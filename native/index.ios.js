var modules={'react-native': require('react-native')};modules['./images/cljs.png']=require('./images/cljs.png');
require('figwheel-bridge').withModules(modules).start('CljsrnApp','ios','localhost');