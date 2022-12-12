import { useState } from "react"
import AlertMessage from "./AlertMessage";

export default function Task({ task, onChangeTodo }) {
    const [alert, setAlert] = useState(false);

    function handleChange(e) {
        setAlert(true);
        onChangeTodo({
            ...task,
            completed: e.target.checked
        })
    }

    return (
        <div className="inline-flex">
            <div className="flex items-center pl-3">
                <input id="checkbox" type="checkbox"
                    className="w-4 h-4 text-blue-600 bg-gray-100 rounded border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600
             dark:ring-offset-gray-700 focus:ring-2 dark:bg-gray-600 dark:border-gray-500"
                    checked={task.completed}
                    onChange={handleChange} />
                <label htmlFor="checkbox" className="py-2 ml-2 w-full text-sm font-medium text-gray-900 dark:text-gray-300">{task.title}</label>
            </div>
            <div className="py-2 ml-2">
                {
                    alert && <AlertMessage type='processing' />
                }
            </div>

        </div>

    )
}