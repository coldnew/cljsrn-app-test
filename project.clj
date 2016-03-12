(defproject cljsrnapp "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.7.170"]
                 [org.omcljs/om "1.0.0-alpha28" :exclusions [cljsjs/react cljsjs/react-dom]]
                 [natal-shell "0.1.6"]]

  :plugins [[lein-cljsbuild "1.1.1"]
            [lein-figwheel "0.5.0-2"]
            [lein-environ "1.0.1"]]

  :clean-targets ["target/" "native/index.ios.js" "native/index.android.js"]

  :aliases {"prod-build" ^{:doc "Recompile code with prod profile."}
            ["do" "clean"
             ["with-profile" "prod" "cljsbuild" "once" "ios"]
             ["with-profile" "prod" "cljsbuild" "once" "android"]]}

  :profiles {:dev {:dependencies [[figwheel-sidecar "0.5.0-2"]
                                  [com.cemerick/piggieback "0.2.1"]]

                   :source-paths ["src" "env/dev"]

                   :cljsbuild    {:builds {:ios     {:source-paths ["src" "env/dev"]
                                                     :figwheel     true
                                                     :compiler     {:output-to     "native/target/ios/not-used.js"
                                                                    :main          "env.ios.main"
                                                                    :output-dir    "native/target/ios"
                                                                    :optimizations :none}}
                                           :android {:source-paths ["src" "env/dev"]
                                                     :figwheel     true
                                                     :compiler     {:output-to     "native/target/android/not-used.js"
                                                                    :main          "env.android.main"
                                                                    :output-dir    "native/target/android"
                                                                    :optimizations :none}}}}

                   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}}

             :prod {:cljsbuild {:builds {:ios     {:source-paths ["src" "env/prod"]
                                                   :compiler     {:output-to     "native/index.ios.js"
                                                                  :main          "env.ios.main"
                                                                  :output-dir    "native/target/ios"
                                                                  :optimizations :simple}}
                                         :android {:source-paths ["src" "env/prod"]
                                                   :compiler     {:output-to     "native/index.android.js"
                                                                  :main          "env.android.main"
                                                                  :output-dir    "native/target/android"
                                                                  :optimizations :simple}}}}}})
