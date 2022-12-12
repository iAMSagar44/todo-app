//import './App.css';
import TaskList from './components/TaskList';

function App() {
  return (
    <div className="App">
      <h1 className="mt-8 mb-12 text-3xl text-center font-extrabold text-gray-900 dark:text-white md:text-5xl lg:text-6xl">
        <span className="text-transparent bg-clip-text bg-gradient-to-r to-emerald-600 from-sky-400">Task Management </span>App</h1>
      <TaskList />

      <footer className="p-4 bg-white rounded-lg shadow md:flex md:items-center md:justify-between md:p-6 dark:bg-gray-800">
        <span className="text-sm text-gray-500 sm:text-center dark:text-gray-400">© 2022. All Rights Reserved.
        </span>
      </footer>

    </div>
  );
}

export default App;
