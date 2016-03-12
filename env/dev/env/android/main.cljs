(ns env.android.main
  (:require [om.next :as om]
            [cljsrnapp.android.core :as core]
            [cljsrnapp.state :as state]
            [figwheel.client :as figwheel :include-macros true]))

(enable-console-print!)

(figwheel/watch-and-reload
  :websocket-url "ws://localhost:3449/figwheel-ws"
  :heads-up-display true
  :jsload-callback #(om/add-root! state/reconciler core/MainView 1))

(core/init)

(def root-el (core/app-root))