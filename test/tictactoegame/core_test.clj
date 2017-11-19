(ns tictactoegame.core-test
  (:require [clojure.test :refer :all]
            [tictactoegame.core :refer :all]))

(deftest test-make-board
  (testing "Make board"
    (testing "default 3x3"
      (is (= (make-board) [1 2 3 4 5 6 7 8 9]))
      (is (= (make-board 3) [1 2 3 4 5 6 7 8 9])))
    (testing "4x4"
      (is (= (make-board 4) [1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16])))
    (testing "5x5"
      (is (= (make-board 5) [1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25])))
    ))

(deftest test-board-lines
  (testing "Board lines"
    (is (= (board-lines (make-board)) '((1 2 3) (4 5 6) (7 8 9))))
    (is (= (board-lines (make-board 3)) '((1 2 3) (4 5 6) (7 8 9))))
    (is (= (board-lines (make-board 4)) '((1 2 3 4) (5 6 7 8) (9 10 11 12) (13 14 15 16))))
    (is (= (board-lines (make-board 5)) '((1 2 3 4 5) (6 7 8 9 10) (11 12 13 14 15) (16 17 18 19 20) (21 22 23 24 25)) ))))

(deftest test-board-picker
  (testing "Board picker"
    (is (= (board-picker [1 2 3 4 5 6 7 8 9] 1 \X) [\X 2 3 4 5 6 7 8 9]))
    (is (= (board-picker [\X 2 3 4 5 6 7 8 9] 2 \O) [\X \O 3 4 5 6 7 8 9]))
    (is (= (board-picker [1 2 3 4 5 6 7 8 9] 4 \X) [1 2 3 \X 5 6 7 8 9]))
    (is (= (board-picker [1 2 3 \X 5 6 7 8 9] 5 \O) [1 2 3 \X \O 6 7 8 9]))
    (is (= (board-picker [1 2 \O \X \O \X 7 8 9] 9 \X) [1 2 \O \X \O \X 7 8 \X]))))

(deftest test-valid-pick?
  (testing "Valid pick?"
    (is (= (valid-pick? [1 2 3 4 5 6 7 8 9] 1) true))
    (is (= (valid-pick? [\X 2 3 4 5 6 7 8 9] 2) true))
    (is (= (valid-pick? [\X \O 3 4 5 6 7 8 9] 3) true))
    (is (= (valid-pick? [1 2 3 \X \O 6 7 8 9] 1) true))
    (is (= (valid-pick? [1 2 3 \X \O 6 7 8 9] 4) false))
    (is (= (valid-pick? [1 2 3 \X \O 6 7 8 9] 5) false))))

(deftest test-board-full?
  (testing "Board full?"
    (is (= (board-full? [\X \X \X \O \O 7 8 9]) false))
    (is (= (board-full? [1 \O \O \X \X \X 7 8 9]) false))
    (is (= (board-full? [1 \O \O 4 5 6 \X \X \X]) false))
    (is (= (board-full? [\X \O \O \O \X \O \X \X \X]) true))
    (is (= (board-full? [\X \X 3 \O 5 6 7 8 9]) false))
    (is (= (board-full? [1 2 3 \X \X 6 \O 8 9]) false))
    (is (= (board-full? [1 2 \O 4 5 6 7 \X \X]) false))
    (is (= (board-full? [\X \O \X \O \X \X \O \X \O]) true))
    (is (= (board-full? [\X \X \X \O \O \X \O \X \O]) true))
    (is (= (board-full? [\O \O \O \X \X \O \X \O \X]) true))
    (is (= (board-full? [\X \X \X \X \O \O \O 10 11 12 13 14 15 16]) false))
    (is (= (board-full? [\X \O \O \O \O \O \X \X \O \X \O \X \O \X \O \X]) true))))

(deftest test-winner?
  (testing "Winner?"
    (testing "Lines"
      (is (= (winner? [\X \X \X \O \O 6 7 8 9]) true))
      (is (= (winner? [1 \O \O \X \X \X 7 8 9]) true))
      (is (= (winner? [1 \O \O 4 5 6 \X \X \X]) true))
      (is (= (winner? [\X \O \O \O \X \O \X \X \X]) true))
      (is (= (winner? [\X \X 3 \O 5 6 7 8 9]) false))
      (is (= (winner? [1 2 3 \X \X 6 \O 8 9]) false))
      (is (= (winner? [1 2 \O 4 5 6 7 \X \X]) false))
      (is (= (winner? [\X \O \X \O \X \X \O \X \O]) false))
      (is (= (winner? [\X \X \X \O \O \X \O \X \O]) true))
      (is (= (winner? [\O \O \O \X \X \O \X \O \X]) true))
      (is (= (winner? [\X \X \X \X \O \O \O 8 9 10 11 12 13 14 15 16]) true))
      (is (= (winner? [\X \O \O \O \O \O \X \X \O \X \O \X \O \X \O \X]) false)))
    (testing "Lines"
      (is (= (winner? [\X \O \X \X \O 6 \X 8 9]) true))
      ; TODO
      )
    (testing "Diagonals"
      (is (= (winner? [\X \O \X \O \X 6 \O 8 \X]) true))
      ; TODO
      )))
