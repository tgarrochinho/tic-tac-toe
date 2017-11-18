(defproject tictactoe "0.1.0-SNAPSHOT"
  :description "Tic Tac Toe Game"
  :url ""
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot tictactoegame.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
