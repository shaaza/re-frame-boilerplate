(ns app.home.events
  (:require [re-frame.core :as re-frame]
            [app.home.subs]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 'Pure' handlers
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Effectful Handlers
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(re-frame/reg-event-fx
 :home/click-alert!
 (fn [{:keys [db]} _]
   {:db (assoc db :loading true)
    :alert "You clicked me!"}))

(re-frame/reg-event-fx
 :home/api-call!
 (fn [{:keys [db]} _]
   {:db db
    :api "/posts/1"}))
