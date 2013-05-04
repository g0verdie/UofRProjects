#lang racket
(define count 0)
(define (nqueens n)
  ;initialize board
  (let ( (board (make-vector n (* n 2))))
    ;run through colums, start recursing
    (if (= (modulo n 2) 1)
        (testSpot board (- (ceiling (/ n 2)) 1) 0 -1)
        (testSpot board (ceiling (/ n 2)) 0 -1)
        )
    )
  )

(define (testSpot board col row interval)
  ;if we're done
  ;(display "testSpot")(newline)
  (if (or (>= col (vector-length board)) (<= col -1))
      (begin
        ;(display board)(newline)
        board
        )
      (begin
        ;check if the space is free
        (if (and (checkRow board row col) (checkDiag board row col) (< row (vector-length board)))
            ;if it is, drop and move on to the next column
            (begin
              (vector-set! board col row)
              (testSpot board (+ col interval) 0 (midInc interval))
              )
            ;if it's not
            (begin
              ;if we're at the end of the row, backtrack
              (if (> row (- (vector-length board) 1))
                  (begin
                    (vector-set! board col (* (vector-length board) 2))
                    (let ((prevCol (- col (midDec interval))))
                      (testSpot board prevCol (+ (vector-ref board prevCol) 1) (midDec interval))
                      )
                    )
                  ;else just go to the next row
                  (testSpot board col (+ row 1) interval)
                  )
              )
            )
        )
      )
  )

(define (midInc interval)
  ;(display interval)(newline)
  (if (< interval 0)
      (+ (abs interval)1)
      (* (+ interval 1) -1)
      )
  )

(define (midDec interval)
  (if (< interval 0)
      (- (abs interval)1)
      (* (- interval 1) -1)
      )
  )
;check to see if there's another rowNum in board
(define (checkRow board row col)
  (let ((conflicts 0))
    (do ((i 0 (+ i 1))) ((>= i (vector-length board)))
      (set! count (+ count 1))
      (if (and (eqv? row (vector-ref board i)) (not (= i col)))
          (begin
            ;(display count)(newline)
            (set! conflicts (+ conflicts 1))
            )
          '()
          )
      )
    (if (> conflicts 0)
        #f
        #t
        )
    )
  )

(define (checkDiag board row col)
  (let ((conflict #t))
    (do ((i 0 (+ i 1))) ((>= i (vector-length board)))
      (begin
        (set! count (+ count 1))
        (if (and (or (=  (+ row (abs (- col i))) (vector-ref board i)) (eqv? (- row (abs (- col i))) (vector-ref board i))) (not (eqv? i col)))
            (begin
              (set! count (+ count 1))
              (set! conflict #f)
              )
            '()
            )
        )
      )
    conflict
    )
  )

(define (runTest n)
  (do ((i 4 (+ i 1))) ((>= i n))
    (display i)(display "- ")
    (nqueens i)
    (display count)
    (newline)
    (set! count 0)
    )
  )

;(runTest 20)
(nqueens 20)(display count)