(ns app.effects
  (:require [re-frame.core :as re-frame]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Effects
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; JavaScript alert()
(re-frame/reg-fx
 :alert
 (fn [text]
 (js/alert text)))
