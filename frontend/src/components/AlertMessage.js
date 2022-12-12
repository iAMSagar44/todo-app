import React from "react";

export default function AlertMessage({ type }) {
    let text = '';
    switch (type) {
        case 'add':
            text = "Item added successfully."
            break;
        case 'update':
            text = "Item updated successfully."
            break;
        case 'processing':
            text = "Syncing..."
            break;
        default:
            text = "Syncing"
            break;
    }
    return (
        <div id="toast-interactive" className="flex text-sm text-blue-700 bg-blue-100 rounded-lg dark:bg-blue-100 dark:text-blue-800" role="alert">
            <div className="flex">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" className="w-4 h-4">
                    <path fillRule="evenodd" d="M4.755 10.059a7.5 7.5 0 0112.548-3.364l1.903 1.903h-3.183a.75.75 0 100 1.5h4.992a.75.75 0 00.75-.75V4.356a.75.75 0 00-1.5 0v3.18l-1.9-1.9A9 9 0 003.306 9.67a.75.75 0 101.45.388zm15.408 3.352a.75.75 0 00-.919.53 7.5 7.5 0 01-12.548 3.364l-1.902-1.903h3.183a.75.75 0 000-1.5H2.984a.75.75 0 00-.75.75v4.992a.75.75 0 001.5 0v-3.18l1.9 1.9a9 9 0 0015.059-4.035.75.75 0 00-.53-.918z" clipRule="evenodd" />
                </svg>
                <span className="sr-only">Info</span>
                <div className="ml-3 text-sm font-normal">
                    <span className="mb-1 text-sm font-semibold text-gray-900 dark:text-white">{text}</span>
                </div>
            </div>
        </div>
    )
}