(ns app.effects
  (:require [re-frame.core :as re-frame]
            [ajax.core :as ajax]
            ))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Effects
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; JavaScript alert()
(re-frame/reg-fx
 :alert
 (fn [text]
 (js/alert text)))


;; API Call
(re-frame/reg-fx
 :api
 (fn [endpoint]
   ;; Console.log the call in dev-mode
   (println (ajax/GET (str "https://jsonplaceholder.typicode.com" endpoint)))))
