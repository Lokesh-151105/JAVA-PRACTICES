# React Hands-on Lab — shoppingapp (Props & ReactDOM.render)

## Objectives

- Define Props
- Explain Default Props
- Identify the differences between State and Props
- Explain `ReactDOM.render()`

## Concepts

### What are Props?
**Props** (short for "properties") are read-only inputs passed from a parent component to a child component. They allow data to flow down the component tree and let components be reused with different data.

```jsx
<Cart itemName="Laptop" price={45000} />
```

Inside the `Cart` component, these values are accessed via the `props` object (or `this.props` in a class component):
```jsx
function Cart(props) {
  return <p>{props.itemName} - ₹{props.price}</p>;
}
```

Key characteristics of props:
- Passed from parent to child (one-way data flow)
- **Read-only** — a component must never modify its own props
- Can be of any type: string, number, array, object, function, etc.

### Default Props
`defaultProps` lets a component specify default values for props that aren't explicitly passed by the parent. This prevents `undefined` values from appearing in the UI.

```jsx
class Cart extends React.Component {
  render() {
    return <p>{this.props.itemName} - ₹{this.props.price}</p>;
  }
}

Cart.defaultProps = {
  itemName: 'Unnamed Item',
  price: 0
};
```
If a `Cart` is rendered without a `price` prop, it will fall back to `0` instead of showing `undefined`.

### State vs Props

| Aspect | State | Props |
|---|---|---|
| Owned by | The component itself | Passed in from the parent component |
| Mutability | Mutable (changed via `setState()` or `useState`) | Immutable / read-only from the receiving component's perspective |
| Purpose | Manages data that changes over the component's lifetime | Passes data and configuration into a component |
| Scope | Local to the component | Flows top-down (parent → child) |
| Triggers re-render | Yes, when updated | Yes, when the parent re-renders with new prop values |

### `ReactDOM.render()`
`ReactDOM.render()` is the method that takes a React element (JSX) and renders it into a specified DOM node in the actual HTML page. It's the entry point that connects the React component tree to the real DOM.

```jsx
import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';

ReactDOM.render(<App />, document.getElementById('root'));
```
- The first argument is the React element/component to render.
- The second argument is the actual DOM node (usually `<div id="root"></div>` in `public/index.html`) where the app is mounted.

> Note: In React 18+, `ReactDOM.render()` has been replaced by `ReactDOM.createRoot(...).render(...)`, but `create-react-app` templates historically use `ReactDOM.render()` directly in `index.js`.

---

## Hands-on: Online Shopping Cart — "shoppingapp"

**Goal:** Create a React application with two class components — `Cart` and `OnlineShopping` — where `OnlineShopping` holds an array of 5 `Cart` items and displays them using props.

### Prerequisites
- Node.js
- NPM
- Visual Studio Code

### Steps

**1. Create the React project**
```bash
npx create-react-app shoppingapp
```

**2. Open the project in VS Code**
```bash
cd shoppingapp
code .
```

**3. Create the `Cart` class component (`Cart.js`)**
With two properties: `Itemname` and `Price`, received via props.
```jsx
import React, { Component } from 'react';

class Cart extends Component {
  render() {
    return (
      <div>
        <p>
          <strong>Item Name:</strong> {this.props.Itemname} &nbsp;|&nbsp;
          <strong>Price:</strong> ₹{this.props.Price}
        </p>
      </div>
    );
  }
}

Cart.defaultProps = {
  Itemname: 'Unnamed Item',
  Price: 0
};

export default Cart;
```

**4. Create the `OnlineShopping` class component (`OnlineShopping.js`)**
Initializes an array of 5 cart items and loops through them to render `Cart` components:
```jsx
import React, { Component } from 'react';
import Cart from './Cart';

class OnlineShopping extends Component {
  constructor(props) {
    super(props);
    this.state = {
      cartItems: [
        { Itemname: 'Laptop', Price: 45000 },
        { Itemname: 'Wireless Mouse', Price: 799 },
        { Itemname: 'Mechanical Keyboard', Price: 2499 },
        { Itemname: 'USB-C Hub', Price: 1299 },
        { Itemname: 'Headphones', Price: 1999 }
      ]
    };
  }

  render() {
    return (
      <div>
        <h1>Online Shopping Cart</h1>
        {this.state.cartItems.map((item, index) => (
          <Cart key={index} Itemname={item.Itemname} Price={item.Price} />
        ))}
      </div>
    );
  }
}

export default OnlineShopping;
```

**5. Update `App.js` to render `OnlineShopping`**
```jsx
import React from 'react';
import OnlineShopping from './OnlineShopping';

function App() {
  return (
    <div className="App">
      <OnlineShopping />
    </div>
  );
}

export default App;
```

**6. Verify `index.js` uses `ReactDOM.render()`**
```jsx
import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';

ReactDOM.render(
  <App />,
  document.getElementById('root')
);
```

**7. Run the application**
```bash
npm start
```

**8. View the output**
Open a browser and navigate to:
```
http://localhost:3000
```

The page displays a heading "Online Shopping Cart" followed by all 5 items, each showing its Item Name and Price, rendered by looping through the `cartItems` array and passing each item's data as props to the `Cart` component.

---

## Project Structure

```
shoppingapp/
├── node_modules/
├── public/
│   └── index.html
├── src/
│   ├── Cart.js
│   ├── OnlineShopping.js
│   ├── App.js
│   ├── index.js
│   └── ...
├── package.json
└── README.md
```

## Conclusion
This hands-on lab covered React Props — how data is passed from parent to child components, how `defaultProps` provides fallback values, the key differences between state and props, and how `ReactDOM.render()` mounts the component tree to the actual DOM. The `shoppingapp` application was built with a `Cart` component (accepting `Itemname` and `Price` as props) and an `OnlineShopping` component that initialized an array of 5 cart items and looped through them to render each as a `Cart`, with the application running successfully at `http://localhost:3000`.
