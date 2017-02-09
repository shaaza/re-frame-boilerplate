(ns app.home.views
  (:require [re-frame.core :as re-frame]))

(defn main []
  (let [name (re-frame/subscribe [:home/name])]
    (fn []
      [:div (str "Hello from " @name ". This is the Home Page.")
       [:div [:a {:href "#/about"} "go to About Page"]]
       [:div [:button.blue {:on-click #(re-frame/dispatch [:home/click-alert])}
              "Click me"]]])))
