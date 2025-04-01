export async function askLoveAdvice(message) {
  const res = await fetch("http://localhost:5000/api/chat", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ message }),
  });
  return res.json();
}

export async function fetchRecommendation(location) {
  const res = await fetch(`http://localhost:5000/api/recommend?location=${location}`);
  return res.json();
}
