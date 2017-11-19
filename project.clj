(defproject tictactoegame "0.1.0-SNAPSHOT"
  :description "Tic Tac Toe Game"
  :dependencies [[org.clojure/clojure "1.8.0"] [org.clojure/math.numeric-tower "0.0.4"]]
  :main ^:skip-aot tictactoegame.console
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
