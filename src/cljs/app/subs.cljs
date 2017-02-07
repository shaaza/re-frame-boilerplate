(ns app.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]))

(def default-db
  {:name "re-frame"
   :loading false
   :home {:name "SampleApp"}})

(re-frame/reg-sub
 :loading
 (fn [db]
   (:loading db)))

(re-frame/reg-sub
 :active-panel
 (fn [db _]
   (:active-panel db)))
