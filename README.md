# OfferWallSDK

Define below dependency into your gradle file

            dependencies {
	                    implementation ("com.github.guptaAbhinav1993:OfferWallSDK:1.0.8")
	        }   

Add this in build.gradle(:app)

	    dataBinding {
        	enabled = true
   	        }


Add this Jitpack maven url

            dependencyResolutionManagement {
		            repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		            repositories {
			        mavenCentral()
			        maven { url 'https://jitpack.io' }
		        }
	        }


Give Internet permission in Manifest file

		<uses-permission android:name="android.permission.INTERNET" />


Make sure network_security_config.xml should be in res/xml folder
Example:

		<?xml version="1.0" encoding="utf-8"?>
		<network-security-config>
    		<base-config cleartextTrafficPermitted="true">
        		<trust-anchors>
            		<certificates src="system" />
        		</trust-anchors>
   		 </base-config>
		</network-security-config>

and mention in to the manifest file in application tag

		android:networkSecurityConfig="@xml/network_security_config"


# IN KOTLIN :


Mention below code in Application class

            SDK().getInstance(this)

Mention below Code in to your Activity class

            val intent: Intent = OfferActivity.getIntentForOfferWall(this)
            startActivity(intent)


# IN JAVA :

Mention below code in Application class

		import com.brandmatic.offerwall.helper.SDK;

		new SDK().getInstance(this);


Mention below Code in to your Activity class

		import com.brandmatic.offerwall.activity.OfferActivity;

  
  		new OfferActivity();
                Intent intent = OfferActivity.Companion.getIntentForOfferWall(this);
                startActivity(intent);

