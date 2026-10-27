/*
 * Bandeau de consentement.
 * - Le silence vaut refus ; la navigation ne vaut pas consentement.
 * - Le choix est mémorisé 6 mois (clé localStorage "rocket-consent"), puis redemandé.
 * - Aujourd'hui AUCUN traceur optionnel n'existe sur le site : le choix est simplement enregistré.
 *   Si un traceur optionnel est ajouté un jour, il ne doit se charger que si
 *   window.rocketConsent && window.rocketConsent.choice === "accepted".
 */
(function () {
  var KEY = 'rocket-consent';
  var MAX_AGE_MS = 1000 * 60 * 60 * 24 * 182; // environ 6 mois
  var banner = document.getElementById('consent');
  if (!banner) { return; }

  function read() {
    try {
      var v = JSON.parse(window.localStorage.getItem(KEY));
      if (v && v.ts && (Date.now() - v.ts) < MAX_AGE_MS) { return v; }
    } catch (e) { /* stockage indisponible : on redemandera */ }
    return null;
  }

  function save(choice) {
    var v = { choice: choice, ts: Date.now() };
    try { window.localStorage.setItem(KEY, JSON.stringify(v)); } catch (e) { /* ignoré */ }
    window.rocketConsent = v;
  }

  function show() { banner.hidden = false; }
  function hide() { banner.hidden = true; }

  window.rocketConsent = read();
  if (!window.rocketConsent) { show(); }

  Array.prototype.forEach.call(document.querySelectorAll('[data-consent]'), function (btn) {
    btn.addEventListener('click', function () {
      save(btn.getAttribute('data-consent'));
      hide();
    });
  });

  Array.prototype.forEach.call(document.querySelectorAll('[data-consent-open]'), function (el) {
    el.addEventListener('click', function (e) {
      e.preventDefault();
      show();
      var first = banner.querySelector('button');
      if (first) { first.focus(); }
    });
  });
})();
