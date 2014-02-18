(defproject apache-logmine "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :jvm-opts ["-Xmx2048M"]
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [com.draines/postal "1.11.0"]
                 [hiccup "1.0.4"]
                 [clj-time "0.6.0"]
		             [lein-light-nrepl "0.0.15"]] ;;Make sure to check what the latest version of lein-light-nrepl is
  :repl-options {:nrepl-middleware [lighttable.nrepl.handler/lighttable-ops]})

