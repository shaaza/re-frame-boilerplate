(ns app.about.views
  (:require [re-frame.core :as re-frame]))

(defn main []
  (fn []
    [:div "This is the About Page."
     [:div [:a {:href "#/"} "go to Home Page"]]]))
