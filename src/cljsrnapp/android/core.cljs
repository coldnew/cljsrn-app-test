(ns cljsrnapp.android.core
  (:require-macros [natal-shell.core :refer [with-error-view]]
                   [natal-shell.components :refer [view text image touchable-highlight]]
                   [natal-shell.alert :refer [alert]])
  (:require [om.next :as om :refer-macros [defui]]
            [cljsrnapp.state :as state]))

(set! js/React (js/require "react-native"))

(defui MainView
  static om/IQuery
  (query [this]
         '[:app/msg])

  Object
  (render [this]
          (with-error-view
            (let [{:keys [app/msg]} (om/props this)]
              (view {:style {:flexDirection "column" :margin 40 :alignItems "center"}}
                    (text
                     {:style
                      {:fontSize 50 :fontWeight "100" :marginBottom 20 :textAlign "center"}}
                     msg)

                    (image
                     {:source
                      {:uri "https://raw.githubusercontent.com/cljsinfo/logo.cljs/master/cljs.png"}
                      :style {:width 80 :height 80 :marginBottom 30}})

                    (touchable-highlight
                     {:style {:backgroundColor "#999" :padding 10 :borderRadius 5}
                      :onPress #(alert "HELLO!")}

                     (text
                      {:style {:color "white" :textAlign "center" :fontWeight "bold"}}
                      "press me")))))))

(defonce app-root (om/factory MainView))

(defn init []
  (om/add-root! state/reconciler MainView 1)
  (.registerComponent (.-AppRegistry js/React) "CljsrnApp" (fn [] app-root)))