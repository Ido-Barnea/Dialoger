# :alarm_clock: Dialoger
A simple library to help developers create beautiful alert dialogs.

# :camera: Preview
<img src="https://github.com/Ido-Barnea/Dialoger/blob/master/images/loading_mockup.png" width="49%"/> <img src="https://github.com/Ido-Barnea/Dialoger/blob/master/images/success_mockup.png" width="49%"/>

# :question: How can I add this to my project?
> Step 1: Add the JitPack repository to your build file
  ```gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
  ```
> Step 2: Add the dependency
  ```gradle
dependencies {
    implementation 'com.github.Ido-Barnea:Dialoger:1.1.6'
}
  ```
  That's it!

# Quick use :fast_forward:
  ```kotlin
val dialog = Dialoger(this, Dialoger.TYPE_LOADING)
    .setTitle("This is a loading dialog...")
    .setDescription("This might take a while...")
    .setDrawable(R.drawable.loading)
    .setProgressBarColor(R.color.purple_200)
    .show()

// Dismiss the dialog after 5 seconds
Handler(Looper.getMainLooper()).postDelayed({
    dialog.dismiss();

  Dialoger(this, Dialoger.TYPE_MESSAGE)
    .setDialogColorTheme(R.color.purple_200)
    .setTitle("New Dialog!")
    .setDescription("This was an amazing success!")
    .setDrawable(R.drawable.success)
    .setButtonText("ALLONS-Y!")
    .setButtonOnClickListener {
        Toast.makeText(this, "dialog button clicked", Toast.LENGTH_SHORT).show()
    }
    .show()
}, 5000);
  ```

# :book: Dialog Types:
- Message
- Loading

# :computer: What can I edit in each dialog?
- Title & description text
- Title & description text color
- Image drawable
- Dialog background color
- Whether the dialog will be dismissed on touch outside of it or not
- Button text ```(Not visible in loading dialog)```
- Button color ```(Not visible in loading dialog)```
- Progress bar color ```(Only visible in loading dialog)```
- Progress bar indeterminate drawable ```(Only visible in loading dialog)```
