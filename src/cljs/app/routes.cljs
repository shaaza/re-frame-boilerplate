(ns app.routes
    (:require-macros [secretary.core :refer [defroute]])
    (:import goog.History)
    (:require [secretary.core :as secretary]
              [goog.events :as events]
              [goog.history.EventType :as EventType]
              [re-frame.core :as re-frame]
              [app.home.views]
              [app.about.views]))

(defn hook-browser-navigation! []
  (doto (History.)
    (events/listen
     EventType/NAVIGATE
     (fn [event]
       (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

(defn app-routes []
  (secretary/set-config! :prefix "#")
  ;; --------------------
  ;; define routes here
  (defroute "/" []
    (re-frame/dispatch [:set-active-panel :home-panel]))

  (defroute "/about" []
    (re-frame/dispatch [:set-active-panel :about-panel]))


  ;; --------------------

  ;; Add corresponding views/panels for routes here:

  (defn- panels [panel-name]
  (case panel-name
    :home-panel [app.home.views/main]
    :about-panel [app.about.views/main]
    [:div]))

  (defn show-panel [panel-name]
    [panels panel-name])

  (hook-browser-navigation!))
