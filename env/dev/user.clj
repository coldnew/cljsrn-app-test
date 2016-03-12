(ns env.dev.user
  (:use [figwheel-sidecar.repl-api :as figwheel]))

;; This namespace is loaded automatically by nREPL

;; read project.clj to get build configs
(def profiles (->> "project.clj"
                   slurp
                   read-string
                   (drop-while #(not= % :profiles))
                   (apply hash-map)
                   :profiles))

(def cljs-builds (get-in profiles [:dev :cljsbuild :builds]))

(defn start-repl
  "Start cljs repl for one or more builds"
  [& build-ids]
  (figwheel/start-figwheel!
   {:build-ids  build-ids
    :all-builds cljs-builds})
  (figwheel/cljs-repl))

(defn stop-repl
  "Stops cljs repl"
  []
  (figwheel/stop-figwheel!))
