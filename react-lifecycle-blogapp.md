# React Hands-on Lab — blogapp (Component Lifecycle)

## Objectives

- Explain the need and benefits of the component lifecycle
- Identify various lifecycle hook methods
- List the sequence of steps in rendering a component

## Concepts

### Need and Benefits of the Component Lifecycle
Every React component goes through a series of phases from creation to removal from the DOM — this is called its **lifecycle**. React exposes hook methods at each phase so developers can run custom code at the right time.

**Why it's needed / Benefits:**
- Allows fetching data from an API right after a component is displayed (e.g., `componentDidMount`)
- Enables cleanup of resources like timers or subscriptions before a component is removed (`componentWillUnmount`)
- Lets developers respond to prop/state changes and re-render efficiently
- Provides a way to catch and handle errors gracefully within a component tree (`componentDidCatch`)
- Improves performance by controlling when a component should or shouldn't re-render

### Lifecycle Phases
React class components go through three main phases:
1. **Mounting** – component is being created and inserted into the DOM
2. **Updating** – component is being re-rendered due to changes in props or state
3. **Unmounting** – component is being removed from the DOM

### Lifecycle Hook Methods

| Phase | Hook Method | Purpose |
|---|---|---|
| Mounting | `constructor()` | Initialize state, bind methods |
| Mounting | `render()` | Return JSX to display |
| Mounting | `componentDidMount()` | Runs after the component is rendered to the DOM; ideal for API calls |
| Updating | `shouldComponentUpdate()` | Decide whether re-render is needed |
| Updating | `render()` | Re-renders JSX |
| Updating | `componentDidUpdate()` | Runs after re-render, useful for DOM operations based on updates |
| Unmounting | `componentWillUnmount()` | Cleanup before component is removed |
| Error Handling | `componentDidCatch()` | Catches JavaScript errors in child component tree and lets the component respond gracefully |

### Sequence of Steps in Rendering a Component
1. `constructor()` – state and props are initialized
2. `render()` – JSX is generated and the Virtual DOM is created
3. React updates the real DOM to match the Virtual DOM
4. `componentDidMount()` – runs once, immediately after the component is added to the DOM
5. On subsequent state/prop changes: `shouldComponentUpdate()` → `render()` → `componentDidUpdate()`
6. `componentWillUnmount()` – runs just before the component is removed from the DOM

---

## Hands-on: Blog Application — "blogapp"

**Goal:** Build a `Posts` class component that fetches blog posts from an API using `componentDidMount()`, displays them, and handles errors using `componentDidCatch()`.

### Prerequisites
- Node.js
- NPM
- Visual Studio Code

### Steps

**1. Create the React project**
```bash
npx create-react-app blogapp
```

**2. Open the project in Visual Studio Code**
```bash
cd blogapp
code .
```

**3. Create `Post.js` in the `src` folder**
Defines a simple class representing a single post's structure:

```jsx
class Post {
  constructor(id, title, body) {
    this.id = id;
    this.title = title;
    this.body = body;
  }
}

export default Post;
```

**4. Create the `Posts` class component in `Posts.js`**
```jsx
import React, { Component } from 'react';

class Posts extends Component {
  constructor(props) {
    super(props);
    this.state = {
      posts: [],
      hasError: false
    };
  }

  render() {
    if (this.state.hasError) {
      return <h2>Something went wrong while loading posts.</h2>;
    }

    return (
      <div>
        <h1>Blog Posts</h1>
        {this.state.posts.map((post) => (
          <div key={post.id}>
            <h3>{post.title}</h3>
            <p>{post.body}</p>
          </div>
        ))}
      </div>
    );
  }
}

export default Posts;
```

**5. Initialize state with a list of posts**
The `constructor()` above already initializes `this.state.posts` as an empty array, which will be populated once the data is fetched.

**6. Create `loadPosts()` method using the Fetch API**
```jsx
loadPosts() {
  fetch('https://jsonplaceholder.typicode.com/posts')
    .then((response) => response.json())
    .then((data) => {
      this.setState({ posts: data });
    })
    .catch((error) => {
      console.error('Error fetching posts:', error);
      this.setState({ hasError: true });
    });
}
```

**7. Implement `componentDidMount()` to call `loadPosts()`**
```jsx
componentDidMount() {
  this.loadPosts();
}
```

**8. Implement `render()` to display title and body**
(Already shown in step 4 — each post's `title` is rendered inside an `<h3>` and its `body` inside a `<p>`.)

**9. Implement `componentDidCatch()` to display errors as alerts**
```jsx
componentDidCatch(error, info) {
  alert(`An error occurred: ${error.toString()}`);
  this.setState({ hasError: true });
}
```

**Full `Posts.js` after all steps:**
```jsx
import React, { Component } from 'react';

class Posts extends Component {
  constructor(props) {
    super(props);
    this.state = {
      posts: [],
      hasError: false
    };
  }

  loadPosts() {
    fetch('https://jsonplaceholder.typicode.com/posts')
      .then((response) => response.json())
      .then((data) => {
        this.setState({ posts: data });
      })
      .catch((error) => {
        console.error('Error fetching posts:', error);
        this.setState({ hasError: true });
      });
  }

  componentDidMount() {
    this.loadPosts();
  }

  componentDidCatch(error, info) {
    alert(`An error occurred: ${error.toString()}`);
    this.setState({ hasError: true });
  }

  render() {
    if (this.state.hasError) {
      return <h2>Something went wrong while loading posts.</h2>;
    }

    return (
      <div>
        <h1>Blog Posts</h1>
        {this.state.posts.map((post) => (
          <div key={post.id}>
            <h3>{post.title}</h3>
            <p>{post.body}</p>
          </div>
        ))}
      </div>
    );
  }
}

export default Posts;
```

**10. Add the `Posts` component to `App.js`**
```jsx
import React from 'react';
import Posts from './Posts';

function App() {
  return (
    <div className="App">
      <Posts />
    </div>
  );
}

export default App;
```

**11. Build and run the application**
```bash
npm start
```

Open a browser and navigate to:
```
http://localhost:3000
```

The page displays a list of blog posts (title as heading, body as paragraph), fetched from `https://jsonplaceholder.typicode.com/posts` and loaded via `componentDidMount()`. If an error occurs while rendering, `componentDidCatch()` displays it as an alert message.

---

## Project Structure

```
blogapp/
├── node_modules/
├── public/
│   └── index.html
├── src/
│   ├── Post.js
│   ├── Posts.js
│   ├── App.js
│   ├── index.js
│   └── ...
├── package.json
└── README.md
```

## Conclusion
This hands-on lab covered the React component lifecycle — its phases (mounting, updating, unmounting), the hook methods available at each phase, and the sequence of steps involved in rendering a component. A `Posts` component was built that uses `componentDidMount()` to fetch data from a REST API (`jsonplaceholder.typicode.com/posts`) and `componentDidCatch()` to gracefully handle and alert on errors. The component was integrated into `App.js` and the application ran successfully at `http://localhost:3000`.
