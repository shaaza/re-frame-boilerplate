(ns app.home.events
  (:require [re-frame.core :as re-frame]
            [app.home.subs]))

(re-frame/reg-event-db
 :home/submit!
 (fn [db]
   (js/alert "you clicked me!")
   (identity db)))
