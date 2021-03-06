(defproject yinch "0.1.0-SNAPSHOT"
  :description "Browser implementation of the Yinsh board game."
  :url "https://github.com/RyanJenkins/Yinch"
  :license {:name "GNU General Public License, version 2"
            :url "https://www.gnu.org/licenses/old-licenses/gpl-2.0.txt"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 ;; NB: This is a local snapshot so I can get srcmaps on js
                 ;; files. Replace with v. "1.8.34" if you're not me.
                 [org.clojure/clojurescript "0.0-SNAPSHOT"
                                    :exclusions [org.apache.ant/ant]]
                 [org.clojure/core.async "0.2.374"]
                 [cljsjs/gl-matrix "2.3.0-jenanwise-0"]
                 [compojure "1.1.6"]
                 [prismatic/dommy "1.1.0"]
                 [com.aphyr/prism "0.1.3"]
                 [com.cemerick/url "0.1.1"]]
  :plugins [[lein-cljsbuild "1.1.3"]
            [lein-ring "0.8.7"]
            [com.aphyr/prism "0.1.3"]]
  :source-paths ["src/clj"
                 "src/cljc"
                 "src/js"]
  :profiles {:cljs-repl {:main ^:skip-aot yinch.repl}
             :test-build {:main ^:skip-aot yinch.build}}
  :aliases {"cljs-repl" ["with-profile" "cljs-repl" "run"]
            "test-build" ["with-profile" "test-build" "run"]}
  :ring {:handler yinch.routes/app}
  :cljsbuild {:repl-listen-port 9000
              :repl-launch-commands
              {"chrome" ["chrome"
                         "http://localhost:3000/repl.html"
                         :stdout ".repl-chrome-out"
                         :stderr ".repl-chrome-err"] }
              :builds [{:source-paths ["src/cljs" "src/cljc"]
                        :compiler {:output-dir "resources/public/js"
                                   :output-to "resources/public/js/main.js"
                                   :source-map true
                                   :libs ["src/js/"]
                                   :optimizations :none
                                   :pretty-print true}}]})
