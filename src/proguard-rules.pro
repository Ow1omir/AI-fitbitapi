# Add any ProGuard configurations specific to this
# extension here.

-keep public class owl.fitbitapi.Fitbitapi {
    public *;
 }
-keeppackagenames gnu.kawa**, gnu.expr**

-optimizationpasses 4
-allowaccessmodification
-mergeinterfacesaggressively

-repackageclasses 'owl/fitbitapi/repack'
-flattenpackagehierarchy
-dontpreverify
