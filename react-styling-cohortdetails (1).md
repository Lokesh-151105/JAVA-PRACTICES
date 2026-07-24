# React Hands-on Lab — CohortDetails Dashboard (Styling Components)

## Objectives

- Understanding the need for styling React components
- Working with CSS Modules and inline styles

## Concepts

### Why Style React Components?
As React applications grow, plain global CSS can lead to class name collisions and unpredictable styling across components. Styling needs to be:
- **Scoped** to the component it belongs to, so styles don't leak or clash
- **Reusable** across similar components
- **Dynamic**, so the UI can change appearance based on data/state (e.g., status-based colors)

React supports several styling approaches, including plain CSS, **CSS Modules**, and **inline styles**.

### CSS Modules
A CSS Module is a `.module.css` file where all class names are scoped locally to the component that imports them by default. This avoids naming collisions between components.

```css
/* Example.module.css */
.box {
  width: 300px;
}
```

```jsx
import styles from './Example.module.css';

function Example() {
  return <div className={styles.box}>Content</div>;
}
```

At build time, `styles.box` resolves to a unique, auto-generated class name (e.g., `Example_box__1a2b3`), so it never conflicts with a `.box` class defined elsewhere.

### Inline Styles
Inline styles are applied directly to an element using the `style` prop, which takes a JavaScript object (camelCase property names, values as strings/numbers).

```jsx
<h3 style={{ color: status === 'ongoing' ? 'green' : 'blue' }}>
  {status}
</h3>
```

Inline styles are useful for styles that depend on dynamic values (like state or props) at runtime, whereas CSS Modules are better suited for static, reusable, scoped styling.

---

## Hands-on: Cohort Details Dashboard

**Scenario:** The My Academy team at Cognizant has a React application that displays details of ongoing and completed cohorts. The task is to style the existing `CohortDetails` component using a CSS Module and inline styles.

### Prerequisites
- Node.js
- NPM
- Visual Studio Code

### Steps

**1. Unzip the provided React application**
Extract the downloaded/attached React application into a folder on your system.

**2. Open command prompt and navigate to the project folder**
```bash
cd path\to\react-application-folder
```

**3. Restore the node packages**
```bash
npm install
```

**4. Open the application in VS Code**
```bash
code .
```

**5. Create a CSS Module file**
In the `src` folder (or alongside the `CohortDetails` component), create a file named:
```
CohortDetails.module.css
```

**6. Define the `.box` class**
```css
.box {
  width: 300px;
  display: inline-block;
  margin: 10px;
  padding: 10px 20px;
  border: 1px solid black;
  border-radius: 10px;
}
```
- `width: 300px` — fixed card width
- `display: inline-block` — allows multiple cards to sit side by side while still respecting box model properties
- `margin: 10px` — spacing around each card
- `padding: 10px 20px` — 10px top/bottom, 20px left/right
- `border: 1px solid black` — thin black border
- `border-radius: 10px` — rounded corners

**7. Define a tag-selector style for `<dt>`**
```css
dt {
  font-weight: 500;
}
```
This applies to every `<dt>` element rendered within the scope of the module without needing a class.

**8. Import the CSS Module in the CohortDetails component**
```jsx
import styles from './CohortDetails.module.css';
```

**9. Apply the `box` class to the container div**
```jsx
<div className={styles.box}>
  {/* cohort details content */}
</div>
```

**10. Style the `<h3>` element based on cohort status**
Use an inline style so the font color is dynamic based on the `status` value — `green` for "ongoing", `blue` for anything else:

```jsx
<h3 style={{ color: cohort.status === 'ongoing' ? 'green' : 'blue' }}>
  {cohort.status}
</h3>
```

**Example: Full `CohortDetails.js` after styling**
```jsx
import React from 'react';
import styles from './CohortDetails.module.css';

function CohortDetails({ cohort }) {
  return (
    <div className={styles.box}>
      <h3 style={{ color: cohort.status === 'ongoing' ? 'green' : 'blue' }}>
        {cohort.status}
      </h3>
      <dl>
        <dt>Cohort Name</dt>
        <dd>{cohort.name}</dd>
        <dt>Start Date</dt>
        <dd>{cohort.startDate}</dd>
        <dt>End Date</dt>
        <dd>{cohort.endDate}</dd>
      </dl>
    </div>
  );
}

export default CohortDetails;
```

**11. Run the application and verify the result**
```bash
npm start
```

Open a browser and navigate to:
```
http://localhost:3000
```

Each cohort should appear as a bordered, rounded card (300px wide, with margin and padding as defined), with the status heading shown in **green** for ongoing cohorts and **blue** for completed (or any other) cohorts, and `<dt>` labels displayed in medium font weight (500).

---

## Project Structure (relevant files)

```
react-application-folder/
├── node_modules/
├── public/
│   └── index.html
├── src/
│   ├── CohortDetails.js
│   ├── CohortDetails.module.css
│   ├── App.js
│   ├── index.js
│   └── ...
├── package.json
└── README.md
```

## Conclusion
This hands-on lab covered the need for styling React components and two key approaches: CSS Modules for scoped, reusable styling, and inline styles for dynamic, data-driven styling. A `box` class and a `dt` tag selector were defined in `CohortDetails.module.css` and applied to the `CohortDetails` component's container, while an inline style was used to conditionally color the cohort status heading green (ongoing) or blue (otherwise). The application was restored, built, and verified successfully at `http://localhost:3000`.
