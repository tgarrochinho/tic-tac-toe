(ns tictactoegame.console
  (:use [tictactoegame.core
         :only [make-board board-size board-lines board-pick valid-pick? board-full? winner?]])
  (:gen-class))

(defn- horizontal-line-str [board]
  (let [c (count board)
        size (board-size board)]
    (str "\n"
         (apply str (repeat (+ c (dec size)) "-")))))

(defn- board-line-str [board-line]
  (str "\n "
       (first board-line)
       (apply str (map #(str " | " %) (rest board-line)))))

; TODO: not working as expected for board > 3x3
(defn- board-str [board]
  (let [h-line (horizontal-line-str board)
        lines (board-lines board)]
    (str (board-line-str (first lines))
         (apply str (map #(str h-line (board-line-str %)) (rest lines))))))

(defn- print-board [board]
  (println (board-str board)))

(defn- read-int []
  (try (Integer/parseInt (read-line))
       (catch NumberFormatException e nil)))

(defn- read-player-number []
  (println "Pick board number: ")
  (flush)
  (read-int))

(defn- pick-player-number [board current-player]
  (let [number (read-player-number)]
    (if (and number (number? number) (valid-pick? board number))
      (board-pick board number current-player)
      (do
        (println "Invalid pick, try again")
        (recur board current-player)))))

(defn play-game [board current-player next-player]
  (do
    (print-board board)
    (let [board-picked (pick-player-number board current-player)]
      (cond
        (winner? board-picked) (println current-player "won")
        (board-full? board-picked) (println "draw")
        :else (recur board-picked next-player current-player)))))

(defn -main
  [& args]
  (play-game (make-board) "X" "O"))
