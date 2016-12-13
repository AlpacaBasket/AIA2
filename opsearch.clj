(defvar start)
(defvar goal)
(defvar ops:)
(defvar :world)

(def ops
    'move   {:pre ( (agent ?agent)
                    (at ?agent ?p1)
                    (connects ?p1 ?p2)
                    )
             :add ((at ?agent ?p2))
             :del ((at ?agent ?p1))
             :txt (move ?p1 to ?p2)
             }
	kill	{:pre ( (agent ?agent)
				    (at ?agent ?target)
					(has ?agent weapon)
					)
			 :add ((is-dead target))
			 :del ((is-living target))
			 :txt (Assassin has killed target)
			 }
	})

(def exampleState
	'#{(at house 12)
	   (at house 24)
	   (at house 41)
	   (at Assassin xx)
	   (at Target xx)
	   (at police xx)
	   (not-have weapon agent)
	   (is-living target)
	   })

