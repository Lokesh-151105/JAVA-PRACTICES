# React Hands-on Lab — StudentApp (Components)

## Objectives

- Explain React components
- Identify the differences between components and JavaScript functions
- Identify the types of components
- Explain class component
- Explain function component
- Define component constructor
- Define render() function

## Concepts

### What is a React Component?
A component is an independent, reusable piece of code that returns a piece of UI (via JSX). React applications are built by composing many such components together, similar to building blocks. Each component can manage its own data (state) and receive data from its parent (props).

### Components vs JavaScript Functions

| Aspect | React Component | Plain JavaScript Function |
|---|---|---|
| Purpose | Returns UI (JSX) to be rendered | Performs logic/computation, returns any value |
| Naming | Must start with an uppercase letter | Can start with lowercase or uppercase |
| Return value | JSX / React elements | Any data type (number, string, object, etc.) |
| State & lifecycle | Can have state and lifecycle methods (class) or hooks (function) | No built-in state or lifecycle |
| Usage | Used like an HTML tag: `<Home />` | Called like a normal function: `functionName()` |
| Rendering | Managed by React's rendering engine | Not tied to any rendering system |

### Types of Components
1. **Class Components** – ES6 classes that extend `React.Component` and implement a `render()` method.
2. **Function Components** – JavaScript functions that accept props and return JSX; can use Hooks (`useState`, `useEffect`, etc.) for state and lifecycle behavior.

### Class Component
A class component is defined using the `class` keyword and extends `React.Component`. It must implement a `render()` method that returns JSX.

```jsx
import React, { Component } from 'react';

class Home extends Component {
  render() {
    return (
      <div>
        <h2>Welcome to the Home page of Student Management Portal</h2>
      </div>
    );
  }
}

export default Home;
```

### Function Component
A function component is a plain JavaScript function that returns JSX. It is simpler and, with Hooks, can do everything a class component can.

```jsx
function Home() {
  return (
    <div>
      <h2>Welcome to the Home page of Student Management Portal</h2>
    </div>
  );
}

export default Home;
```

### Component Constructor
The `constructor()` is a special method in a class component, called before the component is mounted. It is used to:
- Initialize local state (`this.state = {...}`)
- Bind event handler methods to the component instance

```jsx
class Home extends Component {
  constructor(props) {
    super(props); // must call super(props) first
    this.state = {
      message: "Welcome to the Home page of Student Management Portal"
    };
  }

  render() {
    return <h2>{this.state.message}</h2>;
  }
}
```

### render() Function
The `render()` method is required in every class component. It examines `this.props` and `this.state` and returns the JSX describing what should appear on the screen. React calls `render()` whenever the component needs to be updated.

---

## Hands-on: Student Management Portal — "StudentApp"

**Goal:** Create three components — `Home`, `About`, and `Contact` — each displaying a welcome message, and render all three from `App.js`.

### Prerequisites
- Node.js
- NPM
- Visual Studio Code

### Steps

**1. Create the React project**
```bash
npx create-react-app StudentApp
```

**2. Create a Components folder**
Inside `StudentApp/src`, create a new folder named `Components`, and inside it create a file named `Home.js`.

**3. Add code to `Home.js`**
```jsx
import React, { Component } from 'react';

class Home extends Component {
  render() {
    return (
      <div>
        <h2>Welcome to the Home page of Student Management Portal</h2>
      </div>
    );
  }
}

export default Home;
```

**4. Create `About.js`**
Inside `Components`, add a new file `About.js`:

```jsx
import React, { Component } from 'react';

class About extends Component {
  render() {
    return (
      <div>
        <h2>Welcome to the About page of the Student Management Portal</h2>
      </div>
    );
  }
}

export default About;
```

**5. Create `Contact.js`**
Repeat the same pattern for the `Contact` component inside `Components`:

```jsx
import React, { Component } from 'react';

class Contact extends Component {
  render() {
    return (
      <div>
        <h2>Welcome to the Contact page of the Student Management Portal</h2>
      </div>
    );
  }
}

export default Contact;
```

**6. Edit `App.js` to invoke all three components**
```jsx
import React from 'react';
import Home from './Components/Home';
import About from './Components/About';
import Contact from './Components/Contact';

function App() {
  return (
    <div className="App">
      <Home />
      <About />
      <Contact />
    </div>
  );
}

export default App;
```

**7. Run the application**
```bash
cd StudentApp
npm start
```

**8. View the output**
Open a browser and navigate to:
```
http://localhost:3000
```

The page displays, one below the other:
> **Welcome to the Home page of Student Management Portal**
> **Welcome to the About page of the Student Management Portal**
> **Welcome to the Contact page of the Student Management Portal**

---

## Project Structure

```
StudentApp/
├── node_modules/
├── public/
│   └── index.html
├── src/
│   ├── Components/
│   │   ├── Home.js
│   │   ├── About.js
│   │   └── Contact.js
│   ├── App.js
│   ├── index.js
│   └── ...
├── package.json
└── README.md
```

## Conclusion
This hands-on lab covered React components — the difference between components and plain JavaScript functions, class vs function components, the role of the constructor, and the render() method. Three reusable class components (`Home`, `About`, `Contact`) were created inside a `Components` folder for the `StudentApp` project and rendered together in `App.js`, with the app running successfully at `http://localhost:3000`.
