(defvar *rules*)
(defvar *facts*)
(defvar *goals*)

;--------------
; Goal Handling
;--------------

(defun is-fact (g)
	; Check if goal is a fact
	(eq 'fact (first g)))

(defun is-rule (g)
	; Check if goal is a rule
	(eq 'rule (first g)))

(defun print-goal-stack ()
	(format t "~&goal stack....")
	(pprint *goals*)
	(format t "~&")
	)

defun find-path [seeker target]
()
	
defun move-around [person]
()

defun avoid-hunter [prey predator]
()

;------
; Rules
;------

(setf *rules*
      )

(defparameter *facts*
	)
