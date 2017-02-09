(ns app.events
    (:require [re-frame.core :as re-frame]
              [app.subs :as subs]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 'Pure' handlers
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   subs/default-db))

(re-frame/reg-event-db
 :set-active-panel
 (fn [db [_ active-panel]]
   (assoc db :active-panel active-panel)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Effectful Handlers
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; API Call Handlers ;;
;; Each API call must have event-handlers for:
;; a) Request event
;; b) Success event
;; c) Error event
