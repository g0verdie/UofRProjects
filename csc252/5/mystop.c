/* 
 * mystop.c - Another handy routine for testing your tiny shell
 * 
 * usage: mystop <n>
 * Sleeps for <n> seconds and sends SIGTSTP to itself.
 *
 */
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <signal.h>

int main(int argc, char **argv) 
{
	printf("in mystop \n");
    int i, secs;
    pid_t pid; 

    if (argc != 2) {
	fprintf(stderr, "Usage: %s <n>\n", argv[0]);
	exit(0);
    }
    secs = atoi(argv[1]);

	
    for (i=0; i < secs; i++){
		printf("sleeping \n");
       sleep(1);
	   printf("sleep2 \n");
	  }
	
	printf("about to get id\n");
    pid = getpid(); 
	printf("got id\n");

    if (kill(pid, SIGTSTP) < 0){
       		fprintf(stderr, "kill (tstp) error");
	}
	printf("done \n");
    exit(0);

}
