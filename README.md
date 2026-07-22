# React Hands-on Lab — myfirstreact

## Objectives

- Define SPA and its benefits
- Define React and identify its working
- Identify the differences between SPA and MPA
- Explain Pros & Cons of Single-Page Application
- Explain about React
- Define Virtual DOM
- Explain Features of React

## Concepts

### What is an SPA (Single-Page Application)?
An SPA is a web application that loads a single HTML page and dynamically updates content as the user interacts with the app, instead of loading entirely new pages from the server. Only data is exchanged with the server (usually via APIs), and the UI is re-rendered on the client side.

**Benefits of SPA**
- Faster user experience after the initial load (no full page reloads)
- Smooth, app-like navigation
- Reduced server load, since only data (not full pages) is fetched
- Easier separation of front end and back end (API-driven architecture)
- Better caching of static assets

### SPA vs MPA (Multi-Page Application)

| Aspect | SPA | MPA |
|---|---|---|
| Page reloads | Only one initial load; content updates dynamically | Full page reload on every navigation |
| Speed (after load) | Faster, since only data is fetched | Slower, since HTML is re-rendered each time |
| SEO | Harder to optimize (needs extra setup) | Easier, since each page has its own URL/content |
| Development | Single codebase, component-driven | Multiple templates/pages |
| Examples | Gmail, Facebook, Twitter | Traditional e-commerce/news sites |

### Pros & Cons of SPA

**Pros**
- Fast and responsive after initial load
- Better user experience, feels like a native app
- Efficient use of bandwidth (only data transferred)
- Clear separation between front end and back end

**Cons**
- Initial load can be slower (larger JS bundle)
- SEO requires additional configuration (e.g., server-side rendering)
- Browser history/navigation needs to be handled manually
- Heavier reliance on JavaScript; fails if JS is disabled

### What is React?
React is an open-source JavaScript library developed by Facebook (Meta) for building user interfaces, particularly single-page applications. It allows developers to build encapsulated, reusable UI components that manage their own state, and efficiently update the UI when data changes.

**How React Works**
- The UI is broken down into components.
- Each component has its own state and props.
- React uses a Virtual DOM to determine the minimal set of changes needed to update the real DOM.
- When state/props change, React re-renders the component and reconciles the Virtual DOM with the real DOM efficiently.

### Virtual DOM
The Virtual DOM is a lightweight, in-memory representation of the actual DOM. When the state of a component changes:
1. React creates a new Virtual DOM tree.
2. It compares (diffs) this new tree with the previous Virtual DOM tree.
3. React calculates the minimal number of changes required.
4. Only those changes are applied to the real DOM (reconciliation).

This makes UI updates fast and efficient compared to directly manipulating the real DOM.

### Features of React
- **Component-Based Architecture** – UI is built using independent, reusable components.
- **Virtual DOM** – Efficient rendering and updates.
- **JSX** – Syntax extension that allows writing HTML-like code in JavaScript.
- **One-Way Data Binding** – Predictable data flow from parent to child components.
- **Declarative UI** – Describe what the UI should look like for a given state.
- **Rich Ecosystem** – Large community, libraries (React Router, Redux, etc.).
- **React Hooks** – Enables state and lifecycle features in functional components.

---

## Hands-on: Creating "myfirstreact" App

### Prerequisites
- Node.js
- NPM
- Visual Studio Code

### Steps

**1. Install Node.js and NPM**
Download and install from: https://nodejs.org/en/download/

Verify installation:
```bash
node -v
npm -v
```

**2. Install create-react-app**
```bash
npm install -g create-react-app
```

**3. Create the React application**
```bash
npx create-react-app myfirstreact
```

**4. Navigate into the project folder**
```bash
cd myfirstreact
```

**5. Open the project in Visual Studio Code**
```bash
code .
```

**6. Open `App.js`**
Located at: `myfirstreact/src/App.js`

**7 & 8. Replace the content of `App.js`**

```jsx
import React from 'react';

function App() {
  return (
    <div className="App">
      <h1>welcome to the first session of React</h1>
    </div>
  );
}

export default App;
```

**9. Run the application**
```bash
npm start
```

**10. View the output**
Open a browser and navigate to:
```
http://localhost:3000
```

The page displays the heading:
> **welcome to the first session of React**

---

## Project Structure (relevant files)

```
myfirstreact/
├── node_modules/
├── public/
│   └── index.html
├── src/
│   ├── App.js
│   ├── index.js
│   └── ...
├── package.json
└── README.md
```

## Conclusion
This hands-on lab covered the fundamentals of Single-Page Applications and React, including the Virtual DOM and React's core features. A new React application, `myfirstreact`, was created using `create-react-app`, the default `App.js` was modified to render a custom heading, and the application was successfully run locally at `http://localhost:3000`.
