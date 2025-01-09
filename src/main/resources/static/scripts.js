
document.addEventListener('DOMContentLoaded', () => {
    const registerForm = document.getElementById('registerForm');
    const transactionForm = document.getElementById('transactionForm');
    const transactionsContainer = document.getElementById('transactionsContainer');

    // Handle user registration
    registerForm.addEventListener('submit', async (e) => {
        e.preventDefault();

        const name = document.getElementById('name').value;
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;

        const response = await fetch('/api/users/register', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ name, email, password })
        });

        if (response.ok) {
            alert('User registered successfully!');
            registerForm.reset();
        } else {
            alert('Failed to register user. Please try again.');
        }
    });

    // Handle adding a transaction
    transactionForm.addEventListener('submit', async (e) => {
        e.preventDefault();

        const userId = document.getElementById('userId').value;
        const amount = document.getElementById('amount').value;
        const type = document.getElementById('type').value;

        const response = await fetch('/api/transactions', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ userId, amount, type })
        });

        if (response.ok) {
            alert('Transaction added successfully!');
            transactionForm.reset();
            loadTransactions(userId);
        } else {
            alert('Failed to add transaction. Please try again.');
        }
    });

    // Load transactions for a user
    async function loadTransactions(userId) {
        const response = await fetch(`/api/transactions/${userId}`);

        if (response.ok) {
            const transactions = await response.json();
            transactionsContainer.innerHTML = '';
            transactions.forEach(transaction => {
                const transactionDiv = document.createElement('div');
                transactionDiv.classList.add('transaction');
                transactionDiv.innerHTML = `
                    <p><strong>Type:</strong> ${transaction.type}</p>
                    <p><strong>Amount:</strong> $${transaction.amount}</p>
                    <p><strong>Date:</strong> ${new Date(transaction.date).toLocaleString()}</p>
                `;
                transactionsContainer.appendChild(transactionDiv);
            });
        } else {
            transactionsContainer.innerHTML = '<p>Failed to load transactions. Please try again.</p>';
        }
    }
});
