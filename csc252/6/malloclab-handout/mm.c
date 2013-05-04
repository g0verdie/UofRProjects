/*
 * mm-naive.c - The fastest, least memory-efficient malloc package.
 * 
 * In this naive approach, a block is allocated by simply incrementing
 * the brk pointer.  A block is pure payload. There are no headers or
 * footers.  Blocks are never coalesced or reused. Realloc is
 * implemented directly using mm_malloc and mm_free.
 *
 * NOTE TO STUDENTS: Replace this header comment with your own header
 * comment that gives a high level description of your solution.
 */
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <unistd.h>
#include <string.h>

#include "mm.h"
#include "memlib.h"

#if 0
#define DB(...) fprintf(stderr, __VA_ARGS__)
#else
#define DB(...) do { } while (0)
#endif

/*********************************************************
 * NOTE TO STUDENTS: Before you do anything else, please
 * provide your team information in the following struct.
 ********************************************************/
team_t team = {
    /* Team name */
    "dscarafo",
    /* First member's full name */
    "Dan Scarafoni",
    /* First member's email address */
    "dscarafo@u.rochester.edu",
    /* Second member's full name (leave blank if none) */
    "",
    /* Second member's email address (leave blank if none) */
    ""
};

/* single word (4) or double word (8) alignment */
#define ALIGNMENT 8
#define WSIZE 4
#define DSIZE 8
#define INIT_SIZE (1 << 12)
#define OVERHEAD 8
/* max of 2 ints */
#define MAX(x,y) ((x) > (y)? (x) : (y))
#define BLOCK_SIZE  (ALIGN(sizeof(mm_block)))
/* rounds up to the nearest multiple of ALIGNMENT */
#define ALIGN(size) (((size) + (ALIGNMENT-1)) & ~0x7)
#define SIZE_T_SIZE (ALIGN(sizeof(size_t)))
/* read/write word at p */
#define GET(p)     (*(size_t *) (p))
#define PUT(p,val) (*(size_t *) (p) = (val))
/* get size of fields at p */
#define GET_SIZE(p)  (GET(p) & ~0x7)
#define GET_ALLOC(p) (GET(p) & 0x1)
/* get header/footer of bp */
#define HDRP(bp) ((char *)(bp) - WSIZE)
#define FTRP(bp) ((char *)(bp) + GET_SIZE(HDRP(bp)) - DSIZE)
/* next and previous block from bp */
#define NEXT_BLKP(bp) ((char *)(bp) + GET_SIZE(((char *)(bp) - WSIZE)))
#define PREV_BLKP(bp) ((char *)(bp) - GET_SIZE(((char *)(bp) - DSIZE)))
/* packs size and free bit */
#define PACK(size, free) ((size) | (free))

void* heap_listp;

/*
struct _mm_block {
	int size;
	struct _mm_block* next;
	int is_free;
	
};
typedef struct _mm_block mm_block;
mm_block* head = mem_sbrk(0);
*/

/* 
 * mm_init - initialize the malloc package.
 * setup heap
 */
int mm_init(void)
{
  if((heap_listp = mem_sbrk(4* WSIZE)) == NULL) 
    return -1;
  PUT(heap_listp,0);
  PUT(heap_listp + WSIZE,PACK(OVERHEAD,1));
  PUT(heap_listp + DSIZE,PACK(OVERHEAD,1));
  PUT(heap_listp + WSIZE + DSIZE, PACK(OVERHEAD, 1));
  heap_listp += DSIZE; 
  return 0;
}

/* 
 * mm_malloc - Allocate a block by incrementing the brk pointer.
 *     Always allocate a block whose size %is a multiple of the alignment.
 */
void *mm_malloc(size_t size)
{
  DB("malloc size-%d \n",(int) size);
	size_t asize;
	size_t extendsize;
	void* bp;
	
	if(size <= 0) {
	        DB("\t null \n");
		return NULL;
	}
		
	if(size <= DSIZE) {
		asize = DSIZE + OVERHEAD;
		DB("\t new size-%d \n",(int) asize);
	}
	else {
		asize = DSIZE * ((size + (OVERHEAD) + (DSIZE-1)) / DSIZE);
		DB("\t size-%d \n",(int) asize);
	}
		
	if((bp = find_fit(asize)) != NULL) {
		place(bp,asize);
		return bp;
	}
	DB("gonna have to increase heap\n");
	extendsize = MAX(asize,INIT_SIZE);
	if((bp= extend_heap(extendsize/WSIZE)) == NULL)
		return NULL;
	place(bp,asize);
	return bp;
}

/*
 * mm_free - Freeing a block does nothing.
 */
void mm_free(void *bp)
{
  size_t size =  GET_SIZE(HDRP(bp));	
  PUT(HDRP(bp), PACK(size,0));
  PUT(FTRP(bp), PACK(size,0));
}

/*
 * mm_realloc - Implemented simply in terms of mm_malloc and mm_free
 */
void *mm_realloc(void *ptr, size_t size)
{
    DB("realloc \n");
    void *oldptr = ptr;
    void *newptr;
    size_t copySize;
    
    newptr = mm_malloc(size);
    if (newptr == NULL)
      return NULL;
    copySize = *(size_t *)((char *)oldptr - SIZE_T_SIZE);
    if (size < copySize)
      copySize = size;
    memcpy(newptr, oldptr, copySize);
    mm_free(oldptr);
    return newptr;
}

void place(void* bp, size_t asize) {
  PUT(bp,PACK(asize,0));
  PUT(bp+WSIZE,*((char *)bp));
  PUT(bp + WSIZE+asize,PACK(asize,0));
  PUT(bp + DSIZE+asize, PACK(OVERHEAD, 1));
  return;
}

void* find_fit(size_t asize) {
  void* p = heap_listp;
  while(*NEXT_BLKP(p) > 0) {
    if(GET_ALLOC(p) && (GET_SIZE(p) > asize)) {
      DB("\t\t found fit- %d \n",(int) p);
      return p;
    }
    DB("\t\t in find_fit loop \n");
    p = NEXT_BLKP(p);
  }
  DB("\t returning null\n");
	return NULL;
}


void* extend_heap(size_t words) {
  DB("\t extending heap \n");
  char *bp;
  size_t size;

  /* nudge to the right size */
  DB("\t words- %d \n",(int) words);
  size = (words % 2) ? (words+1) * WSIZE : words * WSIZE;
  DB("\t size- %d \n",(int) size);
  if((unsigned) (bp = mem_sbrk(size)) < 0) {
   DB("\t returning null bp = %d \n",(unsigned)bp);	\
    return NULL;
  }

  /* initialize free block header/footer */
  PUT(HDRP(bp), PACK(size, 0));
  PUT(FTRP(bp), PACK(size, 0));
  PUT(HDRP(NEXT_BLKP(bp)), PACK(0, 1));
  DB("\t returning bp \n");
  return bp;
}














