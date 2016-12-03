(defvar *rules*)
(defvar *facts*)
(defvar *goals*)

;--------------
; Goal Handling
;--------------

(defn is-fact (g)
	; Check if goal is a fact
	(eq 'fact (first g)))

(defn is-rule (g)
	; Check if goal is a rule
	(eq 'rule (first g)))

(defn print-goal-stack ()
	(format t "~&goal stack....")
	(pprint *goals*)
	(format t "~&")
	)

defn find-path [seeker target]
()
	
defn move-around [person]
()

defn avoid-hunter [prey predator]
()

;------------
; Application
;------------

(defstruct operation :action :preconditions :add-list :delete-list)

(defn make-op
  [action preconditions add-list delete-list]
  (struct operation action preconditions add-list delete-list))

(def *state* (ref nil))

(def *ops* (ref nil))

(defn contains-value?
  [coll val]
  (not (nil? (some (partial = val) coll))))

(defn appropriate?
  [goal operation]
  "An op is appropriate to a goal if it is in its add list"
  (contains-value? (:add-list operation) goal))

(defn apply-op
 [op]
 "Print a message and update state if op is applicable"
 (when (every? achieve (:preconditions op))
   (println "Executing: " (:action op))
   (dosync
    (alter *state* 
           (fn [s]
             (union 
              (difference s (:delete-list op))
              (:add-list op)))))))

(defn achieve
  [goal]
  "A goal is achieved if it already holds.  Or if there is an appropriate
   operation for it that is applicable"
  (or (contains-value? @*state* goal)
      (some apply-op (filter (fn [x] (appropriate? goal x))  @*ops*))))

(defn gps
  [state goals ops]
  "General Problem Solver: Achieve all goals using the operations available."
  (when (every? achieve goals) 'solved))

;------
; Rules
;------

(setf *rules*
      )

(defparameter *facts*
	)
