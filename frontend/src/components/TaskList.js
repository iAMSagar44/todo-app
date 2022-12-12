import React, { useEffect, useState, useRef } from "react";
import Task from "./Task";
import AddTodo from "./AddTodo"
import AlertMessage from "./AlertMessage";
import axios from "axios";

function TaskList() {

    const [todoData, setToDoData] = useState([]);
    const [alert, setAlert] = useState(false);
    const dataRetrieved = useRef(false);

    const fetchData = () => {
        console.log("Fetching data");
        return axios.get('http://localhost:8080/todo-app/users/1/todos',
            {
                auth: {
                    username: 'user',
                    password: 'password'
                }
            }
        )
            .then(res => res.data);
    }


    useEffect(() => {
        if ((todoData.length && !alert) || dataRetrieved.current) {
            dataRetrieved.current=false;
            return;
        }
        fetchData().then(data => setToDoData(data));
        dataRetrieved.current=true;
    }, [alert, todoData])


    useEffect(() => {
        if (alert) {
            setTimeout(() => {
                setAlert(false);
            }, 100)
        }
    }, [alert])


    function addTodo(data) {
        console.log(Object.keys(data));
        if (Object.keys(data)) {
            setAlert(true);
        }

    }


    function handleChange(todo) {
        console.log("Inside the handle change function", todo)
        const { id, userId, ...body } = todo;
        console.log("The data being updated is:::", body);
        axios.put(`http://localhost:8080/todo-app/users/1/todo/${id}`,
            body,
            {
                auth: {
                    username: 'user',
                    password: 'password'
                }
            }
        ).then(response => {
            console.log("Update complete", response.status);
            setAlert(true);
        });
    }

    function deleteCompletedTasks() {
        //setToDoData(todoData.filter((t) => !t.completed));
        axios.delete('http://localhost:8080/todo-app/users/1/todos',
            {
                params: {
                    completed: true
                },
                auth: {
                    username: 'user',
                    password: 'password'
                }

            }
        ).then(response => {
            console.log("Delete completed", response.status);
            setAlert(true);
        });
    }

    return (
        <div className="block space-y-10">
            <div className="grid grid-cols-3 justify-evenly">

                <div className="mx-4 mb-2 text-justify rounded-lg shadow-lg bg-red-100">

                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" className="w-12 h-12 fill-amber-500">
                        <path fillRule="evenodd" d="M5.25 9a6.75 6.75 0 0113.5 0v.75c0 2.123.8 4.057 2.118 5.52a.75.75 0 01-.297 1.206c-1.544.57-3.16.99-4.831 1.243a3.75 3.75 0 11-7.48 0 24.585 24.585 0 01-4.831-1.244.75.75 0 01-.298-1.205A8.217 8.217 0 005.25 9.75V9zm4.502 8.9a2.25 2.25 0 104.496 0 25.057 25.057 0 01-4.496 0z" clipRule="evenodd" />
                    </svg>
                    <p className="text-4xl text-center font-bold leading-none text-gray-900 dark:text-white p-8">Pending Tasks</p>
                    <ul className="divide-y divide-gray-200 dark:divide-gray-700">
                        {todoData
                            .filter((data) => !data.completed)
                            .map((data) =>
                                <li className="w-full rounded-t-lg border-b border-gray-200 dark:border-gray-600" key={data.id}>
                                    <Task task={data} onChangeTodo={handleChange} />
                                </li>)}

                    </ul>

                    <AddTodo onAdd={addTodo} />

                </div>

                <div className="mx-4 mb-2 rounded-lg shadow-lg bg-green-100">
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" className="w-12 h-12 fill-green-500">
                        <path fillRule="evenodd" d="M2.25 12c0-5.385 4.365-9.75 9.75-9.75s9.75 4.365 9.75 9.75-4.365 9.75-9.75 9.75S2.25 17.385 2.25 12zm13.36-1.814a.75.75 0 10-1.22-.872l-3.236 4.53L9.53 12.22a.75.75 0 00-1.06 1.06l2.25 2.25a.75.75 0 001.14-.094l3.75-5.25z" clipRule="evenodd" />
                    </svg>
                    <p className="text-4xl text-center font-bold leading-none text-gray-900 dark:text-white p-8">Completed Tasks</p>
                    <ul className="divide-y divide-gray-200 dark:divide-gray-700">
                        {todoData
                            .filter((data) => data.completed)
                            .map((data) =>
                                <li className="w-full rounded-t-lg border-b border-gray-200 dark:border-gray-600" key={data.id}>
                                    <Task task={data} onChangeTodo={handleChange} />
                                </li>)}
                    </ul>
                    <button type="button" className="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 
                font-medium rounded-lg text-sm w-full sm:w-auto mt-4 ml-4 px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 
                dark:focus:ring-blue-800"
                        onClick={deleteCompletedTasks}>Clear completed tasks</button>
                </div>

                <div className="mx-4 mb-2 rounded-lg shadow-lg bg-slate-100">
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" className="w-12 h-12 fill-orange-500">
                        <path fillRule="evenodd" d="M2.625 6.75a1.125 1.125 0 112.25 0 1.125 1.125 0 01-2.25 0zm4.875 0A.75.75 0 018.25 6h12a.75.75 0 010 1.5h-12a.75.75 0 01-.75-.75zM2.625 12a1.125 1.125 0 112.25 0 1.125 1.125 0 01-2.25 0zM7.5 12a.75.75 0 01.75-.75h12a.75.75 0 010 1.5h-12A.75.75 0 017.5 12zm-4.875 5.25a1.125 1.125 0 112.25 0 1.125 1.125 0 01-2.25 0zm4.875 0a.75.75 0 01.75-.75h12a.75.75 0 010 1.5h-12a.75.75 0 01-.75-.75z" clipRule="evenodd" />
                    </svg>
                    <p className="text-4xl text-center font-bold leading-none text-gray-900 dark:text-white p-8">All Tasks</p>
                    <ul className="divide-y divide-gray-200 dark:divide-gray-700">
                        {todoData
                            .map((data) =>
                                <li className="w-full rounded-t-lg border-b border-gray-200 dark:border-gray-600" key={data.id}>
                                    <Task task={data} onChangeTodo={handleChange} />
                                </li>)}
                    </ul>
                </div>
            </div>
            <div className="flex justify-center">
                {
                    alert && <AlertMessage type='update' />
                }
            </div>
        </div>


    )
}

export default TaskList;