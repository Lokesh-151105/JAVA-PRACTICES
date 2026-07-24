# bloggerapp — Hands-on 13: Conditional Rendering, Lists & Keys (ReactJS)

A React app with **3 components** — `Book Details`, `Blog Details`, `Course Details` —
demonstrating as many conditional-rendering techniques as possible, plus list
rendering with `map()` and `key`.

## How to open this in VS Code (for the evaluator)

1. Clone this repo:
   ```
   git clone <your-repo-url>
   cd bloggerapp
   ```
2. Open the folder in VS Code:
   ```
   code .
   ```
3. Install dependencies and run:
   ```
   npm install
   npm start
   ```
4. The app opens automatically at `http://localhost:3000`.

## Project structure

```
bloggerapp/
├── public/
│   └── index.html
├── src/
│   ├── components/
│   │   ├── BookDetails.js     -> && operator, ternary operator, map()+key
│   │   ├── BlogDetails.js     -> if/else function, element variable, map()+key
│   │   └── CourseDetails.js   -> switch statement, IIFE inside JSX, map()+key
│   ├── data.js                -> books, blogs, courses arrays
│   ├── App.js                 -> renders the 3 components side by side
│   ├── App.css
│   └── index.js
└── package.json
```

## Conditional rendering techniques covered

| Technique                       | Where used              |
|----------------------------------|--------------------------|
| Logical `&&` operator            | `BookDetails.js`         |
| Ternary operator (`? :`)         | `BookDetails.js`         |
| `if / else` inside a function    | `BlogDetails.js`         |
| Element variable                 | `BlogDetails.js`         |
| `switch` statement               | `CourseDetails.js`       |
| IIFE inside JSX                  | `CourseDetails.js`       |

## List rendering with keys

Each component uses `array.map()` to render a list of items and assigns a
unique `key` (`book.id`, `blog.id`, `course.id`) to the top-level element
returned from `map()`, as required by React.

## Try it

Open `src/data.js` and set any of `books`, `blogs`, or `courses` to an empty
array `[]` to see each component's "no data" fallback render instead.
