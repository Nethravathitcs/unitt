# Introduction #

The UnitT UI Widgets library is a collection of widgets that you can use in your iOS projects. This article presents a general survey of these widgets and an in-depth discussion of each one.

# Setup Your Project #

  1. Include the QuartzCore and CoreGraphics frameworks
  1. Include the !UnittUIWidgets library in your project. For more information on this, see [Including a Library in Your Xcode Project](xCodeIncludes.md).

# Widgets #

### Home View Controller ###

![http://unitt.googlecode.com/svn/wiki/homePortrait.png](http://unitt.googlecode.com/svn/wiki/homePortrait.png)

The Home View Controller class is meant to give you a scrollable, rotatable view of a large icon grid to open subviews. This is typically placed inside a navigation view controller. It is very customizable and provides a toolbar, if so desired. You can specify an image, label, and UIViewController individually for each item (our example above uses the same image for all items). For more information, see [Using the Home View Controller](UIWidgetsHVC.md).

### Url Navigation Controller ###

![http://unitt.googlecode.com/svn/wiki/urlNavigator.png](http://unitt.googlecode.com/svn/wiki/urlNavigator.png)

The Url Navigation Controller class is meant to give you a navigation controller that can create and push UIViewControllers on itself from a specified URL. There is a persistent variant that also allows you to save the state of your application views and restore them on launch. For more information, see [Using the Url Navigation Controller](UIWidgetsUrlNav.md).

### Glossy Button ###

![http://unitt.googlecode.com/svn/wiki/glossyButtons.png](http://unitt.googlecode.com/svn/wiki/glossyButtons.png)

The Glossy Button gives you two different appearances. A simple glossy button and a gel glossy button. For more information, see [Using the Glossy Button](UIWidgetsGB.md).

### Gradient View ###

![http://unitt.googlecode.com/svn/wiki/gradientView.png](http://unitt.googlecode.com/svn/wiki/gradientView.png)

The Gradient View gives a simple vertical gradient as a background for your view. The start and end colors and alpha values are customizable. For more information, see [Using the Gradient View](UIWidgetsGV.md).