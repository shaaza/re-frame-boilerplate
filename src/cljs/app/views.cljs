(ns app.views
  (:require [re-frame.core :as re-frame]
            [app.routes :as routes]))

;; This is the root parent view.

(defn root-view []
  (let [active-panel (re-frame/subscribe [:active-panel])
        loading? (re-frame/subscribe [:loading])]
    (fn []
      (if @loading?
        [:p "Loading..."]
        [routes/show-panel @active-panel]))))
