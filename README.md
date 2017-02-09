# re-frame boilerplate
A boilerplate to quickly get started with developing large SPAs in ClojureScript using [reagent](https://github.com/reagent-project/reagent), [re-frame](https://github.com/Day8/re-frame) and [secretary](https://github.com/gf3/secretary). Hot-load your CLJS Code into your browser using [lein-figwheel](https://github.com/bhauman/lein-figwheel) for rapid development and visualize your app's data store changes using [re-frisk](https://github.com/flexsurfer/re-frisk).

# Introduction

In a re-frame app, we have a single source of client-side app state, which we call *`app-db`*. To see how a re-frame app is structured, read the [re-frame docs](https://github.com/Day8/re-frame/tree/master/docs). The 6 'dominoes' that form an infinite loop as specified in the re-frame docs are:


1. Dispatching events: views and/or event-handlers can dispatch events using the *`re-frame.core/dispatch`* function.
2. Event handling: event handlers are defined in `events.cljs`. They are pure functions of the *`app-db`* that return a modified *`app-db`*. Thus, changes to the app-db are made only in an event handler.
3. Effect handling: 'effectful event handlers' can describe side-effects as data, which are effected by functions registered in `effects.cljs`.
4. Query (subscriptions): subscriptions extract specific data from the *`app-db*`, and are defined in `subs.cljs`.
5. View: views are defined in `views.cljs` in Hiccup syntax and they describe the HTML as pure data. They can subscribe to a subscription to pass some of the app's state (from *`app-db*`) to the view.
6. DOM: the conversion of a view, which is pure data, into a DOM element is handled entirely by reagent.


We use an event called :initialize-db to initialize this eternal loop on app load, with a predefined initial value for `app-db`, which can be found in `subs.cljs`.

# Usage
```
lein clean
lein figwheel dev
```

Figwheel will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:3449](http://localhost:3449).

### Run tests:

*FIXME*

## Production Build


To compile clojurescript to javascript:

```
lein clean
lein cljsbuild once min
```


## Folder Structure
re-frame boilerplate uses the folder structure suggested in the re-frame docs for larger apps (specified [here](https://github.com/Day8/re-frame/blob/master/docs/Basic-App-Structure.md)). Large independent views are split into panels housed in their own folders, and the event-handlers, subscriptions, views and effects for each panel is defined in the panel's folder.

### Adding a panel
*FIXME*

### App root view
*FIXME*

## App Initialization
The :initialize-db event handler registered in `app.events` initializes the app with the *`default-db`* specified in the `app.subs` namespace. **Note that *`default-db`* is specified for the whole app, and not for each panel.** This can be done if required, although it will increase boilerplate code.

The entire initialization process is described [here](https://github.com/Day8/re-frame/blob/master/docs/Loading-Initial-Data.md).

## Routing
re-frame-boilerplate uses [secretary](https://github.com/gf3/secretary) for client-side routing.

### Adding a route:
1. Add a route path
```
*FIXME*
```
2. Add a panel association.
```
*FIXME*
```

***

Out of the 6 'dominoes' outlined in the introduction, only 4 occupy discrete namespaces in our codebase. #1 and #6 do not occupy a namespace, because:

* in the case of #1, events are dispatched from the views or from another event/effect handler.
* in the case of #6, all the conversion of Hiccup views to HTML is done by Reagent.

So, you app's code will contain only four of the 6 dominoes:

## 1. Event Handling
### Registering event-handlers

An event-handler that is pure (i.e. no side-effects) can be registered using:

```
*FIXME*
```

Depending on the type of event, these events have to be registered in:

#### for global events
Global events are events which:
a) change a value that is used across panels in the `app-db`, i.e. not state that is not local to a panel
b) or events triggered by the root view.

These event-handlers should be registered in the `app.events` namespace.

#### for local events
Local events are events which:
a) change the app-db state local to the panel associated with the event
b) or events triggered by a panel's view

Then event-handlers should be registered in the panel's events namespace, e.g. `app.panel1.events`.

### Registering effectful handlers
For event-handlers that cause side-effects (i.e. event-handlers which are not pure functions of the `app-db`), the [re-frame](https://github.com/Day8/re-frame) docs recommend describing the side effect, and registering 'effectful handlers' (essentially middleware) using `re-frame.core/reg-event-fx`. Stuff like: HTTP GET/POST, dispatching other events, undo, localstore writes, db queries, cookies, event sniffing. This means that, in order to effect a side-effect such as an API call:


1. The event handler must describe a side-effect in pure data, and return the new altered db.
2. An 'effectful handler' must effect this side-effect described in terms of pure data.

For example:
```
*FIXME*
```
##### API Calls
We register effectful handlers to make API calls, as suggested [here](https://github.com/Day8/re-frame/blob/master/docs/Talking-To-Servers.md#talking-to-servers). For making API calls, we have to implement an effect handler - one that effectively takes a 'description of an effect' and executes it. For this, we use *FIXME*

For example:
```
*FIXME*
```
### Event keyword naming conventions
#### for events that are local to a panel
We use a synthetic namespace for events local to a panel, as suggested in the re-frame docs [here](https://github.com/Day8/re-frame/blob/master/docs/Namespaced-Keywords.md)

For example, for a panel named 'home':
```
:home/increment-counter
```

#### for effectful events
(i.e. events which are not pure functions of the app-db. They may dispatch other events, make API calls, call the browser API etc.)

An effectful event is named with an exclamation mark (!) at the end. For example:
```
:home/get-users!
```

## 2. Effects
Effects are described as data in event handlers (registered using `re-frame.core/reg-event-fx`, returning data that described the effect).
Effects are registered using `re-frame.core/reg-fx`. *FIXME*

## 3. Database (Subscriptions to app-db)
The app's global state subscriptions are available in `app.subs`, while the subscriptions for each panel are available in their respective directories. **Note that re-frame makes no distinction between 'global state' and 'state local to each panel'. This is merely a structure we impose on our codebase.**

## 4. Views
##### Panel-specific Views vs Global Views
Views can be either:
1. specific to a panel
Some views are defined in the panel's views namespace (`app.panel1.views`), and are used only by the panel. For example, about page.
2. used across panels
Such views are defined in the `app.views` namespace and can be reused wherever required, even across panels. For example, a navigation bar or a loading throbber.

##### Containers vs Components
Within each views namespace, you will find container views and component views. The distinction is trivial:
1. Containers are views which refer to subscriptions in their function body.
2. Components are views that do not refer to subscriptions in their function body.

This pattern is borrowed from the React ecosystem (called the [Container/Component pattern](https://medium.com/@learnreact/container-components-c0e67432e005#.mb0hzgm3l) and is recommended for use with re-frame [here](https://github.com/Day8/re-frame/wiki/Testing), as components are easier to test.

### Adding a view that is local to a panel
*FIXME*
### Adding a view used across panels
*FIXME*
### Editing the root parent view
*FIXME*
### Styling
CSS styles are specified in `resources/public/index.css`, and the Hiccup syntax allows for specifying classes and IDs of HTML tags in the view functions.

To add a new stylesheet:
```
1. add the css stylesheet in the resources/public folder
2. include the css stylesheet in resources/public/index.html using an html <link> tag.
```






# Configuration:

### Dev-mode

The `config.cljs` file (app.config namespace) contains a `debug?` flag, which when set to true will enable console-logging and re-frisk. [re-frisk](https://github.com/flexsurfer/re-frisk) can be used to watch how the app-db changes with time as a tree-like structure describing app state.

### Client-side Routing
To support non-HTML5 browsers, we use hashbang URLs for client-side routing to prevent older browsers from sending requests to the server. This is set in the app.routes namespace, using:
```
(secretary/set-config! :prefix "#")
```
You can customize this to meet your needs.

# Testing
*FIXME*
### Event handlers
*FIXME*
### Views
*FIXME*
