(ns tictactoegame.core
  (:require [clojure.math.numeric-tower :as math])
  (:gen-class))

(def board-default-size 3)

(defn make-board
  ([] (make-board board-default-size))
  ([board-size] (vec (range 1 (+ 1 (* board-size board-size))))))

(defn- board-size [board]
  (math/sqrt (count board)))

(defn board-lines [board]
  (partition (board-size board) board))

(defn- board-columns [board]
  (apply map list (board-lines board)))

(defn- board-main-diagonal [board]
  (loop [board-lines (board-lines board)
         index (dec (board-size board))
         result '()]
    (if (< index 0)
      result
      (recur board-lines
             (dec index)
             (cons (nth (nth board-lines index) index) result)))))

(defn- board-antidiagonal [board]
  (loop [board-lines (board-lines board)
         line-index (dec (board-size board))
         col-index 0
         result '()]
    (if (< line-index 0)
      result
      (recur board-lines
             (dec line-index)
             (inc col-index)
             (cons (nth (nth board-lines line-index) col-index) result)))))

(defn- board-diagonals [board]
  (concat (list (board-main-diagonal board)) (list (board-antidiagonal board))))

(defn- board-win-combinations [board]
  (concat (board-lines board)
          (board-columns board)
          (board-diagonals board)))

(defn board-picker [board number mark]
  (assoc board (dec number) mark))

(defn valid-pick? [board number]
  (number? (board (dec number))))

(defn board-full? [board]
  (not-any? number? board))

(defn winner? [board]
  (not (nil? (first (filter #(= % 1) (map count (map distinct (board-win-combinations board))))))))
