(define (nqueens n)
  ;initialize board
  (let ( (board (make-vector n (* 10 n))))
    ;run through colums, start recursing
    (colRecurse board 0 n)
    ;(display board)
    )
  )

(define (colRecurse board col n)
  (display "colrecurse, col- ")(display col)(newline)
  (if (= col n)
      (begin
        (display "WIN")
        board
        )
      ;else, go through each row and find an open spot
      (begin
        (do ((i 0 (+ i 1))) ((and (>= i (vector-length board)) (not (eqv? n col))))
          (display "\t row- ")(display i)(newline)
          (display board)(newline)
          (rowRecurse board col i n)
          )
        (display "\t ran out of choices, on column- ")(display col)(newline)(newline)
        (vector-set! board col (* 10 n))
        board
        )
      )
  board
  )

(define (rowRecurse board col row n)
  ;(display "row recurse")(newline)
  (if (eqv? row n)
      (begin
        board
        )
      (begin
        ;if no conflict, place
        ;(display "checking horiz and diag")(newline)
        (if (and (checkRow board row 0 n) (checkDiag board row col))
            (begin
              (display "\tfree space")(newline)
              (vector-set! board col row)
              (colRecurse board (+ col 1) n)
              )
            (if (eqv? row (- n 1))
                (begin
                  (vector-set! board col (* 10 n))
                  (display "reset")(newline)
                  ;(display board)
                  )
                )
            )
        )
      )
  board
  )
;check to see if there's another rowNum in board
(define (checkRow board row i n)
  ;(display "checkRow")(newline)
  (if (eq? i n)
      (begin
        ;(display "pass")(newline)
        #t
        )
      (if (eq? (vector-ref board i) row)
          (begin
            ;(display (vector-ref board i))
            ;(display row)
            #f
            ;(display "fail")(newline)
            )
          (checkRow board row (+ i '1) n)
          )
      )
  )
(define (checkDiag board row col)
  (let ((conflict #t))
    (do ((i 0 (+ i 1))) ((>= i (vector-length board)))
      (begin
        ;(display (+ row (abs (- col i))))(newline)(newline)
        ;(newline)
        (if (and (or (eqv?  (+ row (abs (- col i))) (vector-ref board i)) (eqv? (- row (abs (- col i))) (vector-ref board i))) (not (eqv? i col)))
            (begin
              ;(display i)
              (set! conflict #f)
            )
            )
        )
      )
    ;(display "it worked? ")(display conflict)(newline)(newline)
    conflict
    )
  )

(nqueens 4)

(define (breakLoop n)
  (do ((i 0 (+ i 1))) ((>= i 10))
    (if (= i n)
        #t
        )
    )
  #f
  )
;(breakLoop 4)