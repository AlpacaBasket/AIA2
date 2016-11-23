globals []
breed [assassins assassin]
breed [targets target]
breed [people person]

to setup
  clear-all
  reset-ticks
  setupWorld
  setupAssassin
  setupTarget
  setupPerson
end

to go
  tick
  startAssassin
  startTarget
  startPerson
end

to setupWorld
  ;; Set up environment
end

to setupAssassin
  ;; Place assassin in world
end

to setupTarget
  ;; Place target in world
end

to setupPerson
  ;; Place the rest of the people in world
end

to startAssassin
  ;; Driver for the asssassin's actions
end

to startTarget
  ;; Driver for the target's actions
end

to startPerson
  ;; Driver for the rest of the people's actions
end
