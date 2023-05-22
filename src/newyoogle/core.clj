(ns newyoogle.core
  (:gen-class)
  (:require [org.httpkit.server :as http-kit]
            [hiccup.page :as hp]
            [reitit.ring :as ring]))

(def colors [:span.red :span.green :span.blue :span.yellow :span.purple])
(defn page []
  (hp/html5
    [:head
     [:meta {:charset "utf-8"}]
     [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
     [:link {:rel "stylesheet" :href "https://unpkg.com/tachyons@4.12.0/css/tachyons.min.css"}]
     [:title "NewYoogle"]]
    [:body
     [:section.vh-100.dt-w100.flex.flex-column.items-center.justify-center
      [:p.f1.code.mt0.mb3
       (map #(vec [(rand-nth colors) (str %)]) "NewYoogle")]
      [:form.flex.flex-column.items-center.w-100 {:method "GET" :action "https://google.com/search"}
       [:input.w-50-l.w-75 {:type "text" :name "q"}]
       [:input.code.mt3 {:type "submit" :value "Search"}]]]]))

(def app
  (ring/ring-handler
    (ring/router
      ["/" {:get {:handler (fn [_] {:status 200 :body (page)})}}])))

(defn -main
  [& args]
  (http-kit/run-server app {:port 8080})
  (println "Started NewYoogle"))
