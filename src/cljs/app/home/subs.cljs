(ns app.home.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 :home/name
 (fn [{db :home}]
   (:name db)))
