(defproject defold-editor "2.0.0-SNAPSHOT"
  :description      "Defold game editor"
  :url              "http://doc.defold.com"

  :repositories     {"local" ~(str (.toURI (java.io.File. "localjars")))}

  :plugins          [[lein-protobuf-minimal "0.4.1"]]

  :dependencies     [[org.clojure/clojure                         "1.7.0-alpha5"]
                     [org.clojure/core.cache                      "0.6.3"]
                     [org.clojure/tools.nrepl                     "0.2.3"]
                     [org.clojure/core.async                      "0.1.346.0-17112a-alpha"]
                     [org.clojure/core.match                      "0.2.1"]
                     [org.clojure/tools.macro                     "0.1.2"]
                     [org.clojure/tools.namespace                 "0.2.6"]
                     [org.clojure/tools.macro                     "0.1.2"]
                     [com.stuartsierra/component                  "0.2.2"]
                     [prismatic/schema                            "0.3.1"]
                     [prismatic/plumbing                          "0.3.5"]
                     [inflections/inflections                     "0.9.10"]
                     [com.google.protobuf/protobuf-java           "2.3.0"]
                     [org.jogamp.gluegen/gluegen-rt-main          "2.0.2"]
                     [org.jogamp.jogl/jogl-all-main               "2.0.2"]
                     [org.slf4j/slf4j-api                         "1.6.4"]
                     [ch.qos.logback/logback-classic              "1.1.2"]
                     [joda-time/joda-time                         "2.1"]

                     ;; These are installed in a local repository
                     [javax/vecmath                               "1.5.2"]
                     [javax/inject                                "1.0.0"]
                     [com.sun.jna/jna                             "3.4.1"]
                     [com.sun.jna/platform                        "3.4.1"]
                     [dlib/upnp                                   "0.1"]

                     ;; These are the plugins built by Eclipse.
                     ;; We pick these up from a local repo.
                     ;; Use scripts/install_jars to populate the repo.
                     [com.dynamo.cr/com.dynamo.cr.bob "1.0.0"]
                     [com.dynamo.cr/com.dynamo.cr.common "1.0.0"]
                     [com.dynamo.cr/com.dynamo.cr.sceneed "1.0.0"]
                     [com.dynamo.cr/com.dynamo.cr.properties "1.0.0"]
                     [com.dynamo.cr/com.dynamo.cr.editor "1.0.0"]
                     [com.dynamo.cr/com.dynamo.cr.editor.core "1.0.0"]
                     [com.dynamo.cr/com.dynamo.cr.editor.fs "1.0.0"]
                     [com.dynamo.cr/com.dynamo.cr.editor.ui "1.0.0"]
                     [com.dynamo.cr/com.dynamo.cr.editor.core "1.0.0"]
                     [com.dynamo.cr/com.dynamo.cr.client "1.0.0"]
                     [com.dynamo.cr/com.dynamo.cr.editor.fs "1.0.0"]
                     [com.dynamo.cr/com.dynamo.cr.builtins "1.0.0"]
                     [com.dynamo.cr/com.dynamo.cr.engine "1.0.0"]
                     [com.dynamo.cr/com.dynamo.cr.rlog "1.0.0"]
                     [com.dynamo.cr/com.dynamo.cr.protobind "1.0.0"]
                     [com.dynamo.cr/com.dynamo.cr.branchrepo "1.0.0"]
                     [com.dynamo.cr/com.dynamo.cr.dgit "1.0.0"]

                     ;; "Legacy" dependencies from here down.
                     ;; Remove these once we divorce from Eclipse
                     [org.eclipse/com.ibm.icu "52.1.0"]
                     [org.eclipse/javax.activation "1.1.0"]
                     [org.eclipse/javax.annotation "1.2.0"]
                     [org.eclipse/javax.mail "1.4.0"]
                     [org.eclipse/javax.persistence "2.1.0"]
                     [org.eclipse/javax.servlet "2.4.0"]
                     [org.eclipse/javax.servlet "3.0.0"]
                     [org.eclipse/javax.xml "1.3.4"]
                     [org.eclipse/org.apache.batik.css "1.7.0"]
                     [org.eclipse/org.apache.batik.util.gui "1.7.0"]
                     [org.eclipse/org.apache.batik.util "1.7.0"]
                     [org.eclipse/org.eclipse.ant.core "3.3.0"]
                     [org.eclipse/org.eclipse.compare.core "3.5.400"]
                     [org.eclipse/org.eclipse.compare "3.5.501"]
                     [org.eclipse/org.eclipse.core.commands "3.6.100"]
                     [org.eclipse/org.eclipse.core.contenttype "3.4.200"]
                     [org.eclipse/org.eclipse.core.databinding.observable "1.4.1"]
                     [org.eclipse/org.eclipse.core.databinding.property "1.4.200"]
                     [org.eclipse/org.eclipse.core.databinding "1.4.2"]
                     [org.eclipse/org.eclipse.core.expressions "3.4.600"]
                     [org.eclipse/org.eclipse.core.filebuffers "3.5.400"]
                     [org.eclipse/org.eclipse.core.filesystem.java7 "1.0.0"]
                     [org.eclipse/org.eclipse.core.filesystem.macosx "1.3.0"]
                     [org.eclipse/org.eclipse.core.filesystem "1.4.100"]
                     [org.eclipse/org.eclipse.core.jobs "3.6.0"]
                     [org.eclipse/org.eclipse.core.net "1.2.200"]
                     [org.eclipse/org.eclipse.core.resources "3.9.1"]
                     [org.eclipse/org.eclipse.core.runtime.compatibility "3.2.200"]
                     [org.eclipse/org.eclipse.core.runtime "3.10.0"]
                     [org.eclipse/org.eclipse.core.variables "3.2.800"]
                     [org.eclipse/org.eclipse.debug.core "3.9.1"]
                     [org.eclipse/org.eclipse.e4.core.commands "0.10.2"]
                     [org.eclipse/org.eclipse.e4.core.contexts "1.3.100"]
                     [org.eclipse/org.eclipse.e4.core.di.extensions "0.12.0"]
                     [org.eclipse/org.eclipse.e4.core.di "1.4.0"]
                     [org.eclipse/org.eclipse.e4.core.services "1.2.1"]
                     [org.eclipse/org.eclipse.e4.tools.emf.liveeditor "0.12.0"]
                     [org.eclipse/org.eclipse.e4.tools.emf.ui.script.js "0.12.0"]
                     [org.eclipse/org.eclipse.e4.tools.emf.ui "0.12.0"]
                     [org.eclipse/org.eclipse.e4.tools.services "0.12.0"]
                     [org.eclipse/org.eclipse.e4.tools.spy "0.1.0"]
                     [org.eclipse/org.eclipse.e4.ui.bindings "0.10.200"]
                     [org.eclipse/org.eclipse.e4.ui.css.core "0.10.100"]
                     [org.eclipse/org.eclipse.e4.ui.css.swt.theme "0.9.300"]
                     [org.eclipse/org.eclipse.e4.ui.css.swt "0.11.101"]
                     [org.eclipse/org.eclipse.e4.ui.di "1.0.0"]
                     [org.eclipse/org.eclipse.e4.ui.model.workbench "1.1.0"]
                     [org.eclipse/org.eclipse.e4.ui.services "1.1.0"]
                     [org.eclipse/org.eclipse.e4.ui.widgets "1.0.0"]
                     [org.eclipse/org.eclipse.e4.ui.workbench.addons.swt "1.1.1"]
                     [org.eclipse/org.eclipse.e4.ui.workbench.renderers.swt.cocoa "0.11.200"]
                     [org.eclipse/org.eclipse.e4.ui.workbench.renderers.swt "0.12.1"]
                     [org.eclipse/org.eclipse.e4.ui.workbench.swt "0.12.100"]
                     [org.eclipse/org.eclipse.e4.ui.workbench3 "0.12.0"]
                     [org.eclipse/org.eclipse.e4.ui.workbench "1.2.1"]
                     [org.eclipse/org.eclipse.ecf.filetransfer "5.0.0"]
                     [org.eclipse/org.eclipse.ecf.identity "3.4.0"]
                     [org.eclipse/org.eclipse.ecf.provider.filetransfer.ssl "1.0.0"]
                     [org.eclipse/org.eclipse.ecf.provider.filetransfer "3.2.200"]
                     [org.eclipse/org.eclipse.ecf.ssl "1.1.0"]
                     [org.eclipse/org.eclipse.ecf "3.4.0"]
                     [org.eclipse/org.eclipse.emf.common "2.10.1"]
                     [org.eclipse/org.eclipse.emf.databinding.edit "1.3.0"]
                     [org.eclipse/org.eclipse.emf.databinding "1.3.0"]
                     [org.eclipse/org.eclipse.emf.ecore.change "2.10.0"]
                     [org.eclipse/org.eclipse.emf.ecore.xmi "2.10.1"]
                     [org.eclipse/org.eclipse.emf.ecore "2.10.1"]
                     [org.eclipse/org.eclipse.emf.edit "2.10.1"]
                     [org.eclipse/org.eclipse.equinox.app "1.3.200"]
                     [org.eclipse/org.eclipse.equinox.bidi "0.10.0"]
                     [org.eclipse/org.eclipse.equinox.common "3.6.200"]
                     [org.eclipse/org.eclipse.equinox.concurrent "1.1.0"]
                     [org.eclipse/org.eclipse.equinox.ds "1.4.200"]
                     [org.eclipse/org.eclipse.equinox.event "1.3.100"]
                     [org.eclipse/org.eclipse.equinox.frameworkadmin.equinox "1.0.500"]
                     [org.eclipse/org.eclipse.equinox.frameworkadmin "2.0.100"]
                     [org.eclipse/org.eclipse.equinox.p2.artifact.repository "1.1.300"]
                     [org.eclipse/org.eclipse.equinox.p2.core "2.3.0"]
                     [org.eclipse/org.eclipse.equinox.p2.director.app "1.0.300"]
                     [org.eclipse/org.eclipse.equinox.p2.director "2.3.100"]
                     [org.eclipse/org.eclipse.equinox.p2.engine "2.3.0"]
                     [org.eclipse/org.eclipse.equinox.p2.garbagecollector "1.0.200"]
                     [org.eclipse/org.eclipse.equinox.p2.jarprocessor "1.0.300"]
                     [org.eclipse/org.eclipse.equinox.p2.metadata.repository "1.2.100"]
                     [org.eclipse/org.eclipse.equinox.p2.metadata "2.2.0"]
                     [org.eclipse/org.eclipse.equinox.p2.operations "2.4.0"]
                     [org.eclipse/org.eclipse.equinox.p2.publisher.eclipse "1.1.200"]
                     [org.eclipse/org.eclipse.equinox.p2.publisher "1.3.0"]
                     [org.eclipse/org.eclipse.equinox.p2.repository.tools "2.1.0"]
                     [org.eclipse/org.eclipse.equinox.p2.repository "2.3.0"]
                     [org.eclipse/org.eclipse.equinox.p2.touchpoint.eclipse "2.1.200"]
                     [org.eclipse/org.eclipse.equinox.p2.touchpoint.natives "1.1.100"]
                     [org.eclipse/org.eclipse.equinox.p2.transport.ecf "1.1.0"]
                     [org.eclipse/org.eclipse.equinox.p2.ui.sdk "1.0.300"]
                     [org.eclipse/org.eclipse.equinox.p2.ui "2.3.0"]
                     [org.eclipse/org.eclipse.equinox.p2.updatesite "1.0.400"]
                     [org.eclipse/org.eclipse.equinox.preferences "3.5.200"]
                     [org.eclipse/org.eclipse.equinox.registry "3.5.400"]
                     [org.eclipse/org.eclipse.equinox.security.macosx "1.100.200"]
                     [org.eclipse/org.eclipse.equinox.security.ui "1.1.200"]
                     [org.eclipse/org.eclipse.equinox.security "1.2.0"]
                     [org.eclipse/org.eclipse.equinox.simpleconfigurator.manipulator "2.0.0"]
                     [org.eclipse/org.eclipse.equinox.simpleconfigurator "1.1.0"]
                     [org.eclipse/org.eclipse.equinox.util "1.0.500"]
                     [org.eclipse/org.eclipse.help "3.6.0"]
                     [org.eclipse/org.eclipse.jdt.compiler.apt "1.1.0"]
                     [org.eclipse/org.eclipse.jdt.compiler.tool "1.0.300"]
                     [org.eclipse/org.eclipse.jdt.core "3.10.0"]
                     [org.eclipse/org.eclipse.jdt.launching "3.7.101"]
                     [org.eclipse/org.eclipse.jetty.continuation "8.1.14"]
                     [org.eclipse/org.eclipse.jetty.http "8.1.14"]
                     [org.eclipse/org.eclipse.jetty.io "8.1.14"]
                     [org.eclipse/org.eclipse.jetty.server "8.1.14"]
                     [org.eclipse/org.eclipse.jetty.util "8.1.14"]
                     [org.eclipse/org.eclipse.jface.databinding "1.6.200"]
                     [org.eclipse/org.eclipse.jface.text "3.9.1"]
                     [org.eclipse/org.eclipse.jface "3.10.1"]
                     [org.eclipse/org.eclipse.ltk.core.refactoring "3.6.101"]
                     [org.eclipse/org.eclipse.ltk.ui.refactoring "3.7.100"]
                     [org.eclipse/org.eclipse.osgi.compatibility.state "1.0.1"]
                     [org.eclipse/org.eclipse.osgi.services "3.4.0"]
                     [org.eclipse/org.eclipse.osgi "3.10.1"]
                     [org.eclipse/org.eclipse.pde.core "3.10.1"]
                     [org.eclipse/org.eclipse.search "3.9.100"]
                     [org.eclipse/org.eclipse.swt.cocoa.macosx.x86_64 "3.103.1"]
                     [org.eclipse/org.eclipse.swt "3.103.1"]
                     [org.eclipse/org.eclipse.team.core "3.7.0"]
                     [org.eclipse/org.eclipse.team.ui "3.7.100"]
                     [org.eclipse/org.eclipse.text "3.5.300"]
                     [org.eclipse/org.eclipse.ui.cocoa "1.1.100"]
                     [org.eclipse/org.eclipse.ui.console "3.5.300"]
                     [org.eclipse/org.eclipse.ui.editors "3.8.200"]
                     [org.eclipse/org.eclipse.ui.forms "3.6.100"]
                     [org.eclipse/org.eclipse.ui.ide "3.10.1"]
                     [org.eclipse/org.eclipse.ui.navigator.resources "3.4.700"]
                     [org.eclipse/org.eclipse.ui.navigator "3.5.401"]
                     [org.eclipse/org.eclipse.ui.net "1.2.200"]
                     [org.eclipse/org.eclipse.ui.trace "1.0.201"]
                     [org.eclipse/org.eclipse.ui.views.log "1.0.500"]
                     [org.eclipse/org.eclipse.ui.views.properties.tabbed "3.6.0"]
                     [org.eclipse/org.eclipse.ui.views "3.7.0"]
                     [org.eclipse/org.eclipse.ui.workbench.texteditor "3.9.0"]
                     [org.eclipse/org.eclipse.ui.workbench "3.106.1"]
                     [org.eclipse/org.eclipse.ui "3.106.0"]
                     [org.eclipse/org.eclipse.update.configurator "3.3.300"]
                     [org.eclipse/org.hamcrest.core "1.3.0"]
                     [org.eclipse/org.mozilla.javascript "1.7.2"]
                     [org.eclipse/org.sat4j.core "2.3.5"]
                     [org.eclipse/org.sat4j.pb "2.3.5"]
                     [org.eclipse/org.slf4j.api "1.7.2"]
                     [org.eclipse/org.w3c.css.sac "1.3.1"]
                     [org.eclipse/org.w3c.dom.events "3.0.0"]
                     [org.eclipse/org.w3c.dom.smil "1.0.0"]
                     [org.eclipse/org.w3c.dom.svg "1.1.0"]

                     ;; Dependencies of our dependencies
                     ;; These duplicate deps from the bundle xmls
                     [com.jcraft/jsch "0.1.44-1"]
                     [javolution/javolution "5.5.1"]
                     [commons-cli/commons-cli "1.2"]
                     [net.jpountz.lz4/lz4 "1.2.0"]
                     [net.java/openmali "1.0"]
                     [org.eclipse/org.eclipse.jgit "1.2.0"]
                     [org.eclipse/org.eclipse.jgit.http.server "1.2.0"]
                     [org.glassfish.grizzly/grizzly-http-all      "2.1.2"]
                     [org.apache.httpcomponents/httpcore          "4.2"]
                     [org.apache.httpcomponents/httpclient        "4.2"]
                     [commons-io/commons-io                       "2.4"]
                     [commons-lang/commons-lang                   "2.6"]
                     [commons-codec/commons-codec                 "1.9"]
                     [commons-configuration/commons-configuration "1.10"]
                     [org.apache.commons/commons-lang3            "3.3.2"]
                     [com.sun.jersey/jersey-core                  "1.18.1"]
                     [com.sun.jersey/jersey-client                "1.18.1"]
                     [org.codehaus.jackson/jackson-mapper-asl     "1.9.13"]
                     [com.google.guava/guava                      "r09"]
                     [com.google.inject/guice                     "3.0"]]

  :main              ^:skip-aot editor

  :source-paths      ["src/clj"
                      "../com.dynamo.cr/com.dynamo.cr.sceneed2/src/clj"]

  :java-source-paths ["src/java"
                      "../com.dynamo.cr/com.dynamo.cr.sceneed2/src/java"]

  :resource-paths    ["../com.dynamo.cr/com.dynamo.cr.builtins/builtins"]

  :protobuf-includes ["../engine/gamesys/proto"
                      "../engine/ddf/src"
                      "../tmp/dynamo_home/ext/include"]

  :aliases           {"ci" ["do" "test," "uberjar"]}

  :profiles          {:uberjar {:main editor.Main
                                :aot [editor]}
                      :dev     {:dependencies [[org.clojure/test.check "0.5.8"]
                                               [org.mockito/mockito-core "1.8.5"]]
                                :repl-options {:port    4001}
                                :proto-path   "test/proto"}

                      :doc     {:dependencies [[clj-aws-s3/clj-aws-s3 "0.3.10"]
                                               [codox/codox.core "0.8.10"]
                                               [org.apache.httpcomponents/httpcore "4.2"]
                                               [clojure-ini/clojure-ini "0.0.2"]]}})
