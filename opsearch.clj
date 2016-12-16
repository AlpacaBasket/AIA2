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
					(has-weapon Assassin)
					)
			 :add ((is-dead target))
			 :del ((is-living target))
			 :txt (Assassin has killed target)
			 }
	
	pick-wep {:pre ( (at Assassin ?p1)
			 (at weapon ?p1)
			 (no-weapon Assassin)
			       )
		  :add ((has-weapon Assassin))
		  :del ((no-weapon Assassin))
		  :txt (Assassin has picked up weapon)
	)

(def exampleState
	'#{(at Assassin xx)
	   (at Target xx)
	   (no-weapon Assassin)
	   (is-living target)
	   })

