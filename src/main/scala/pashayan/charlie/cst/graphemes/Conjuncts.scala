package pashayan.charlie.cst.graphemes

object Conjuncts {
  val All: Seq[Seq[CharPart]] = Seq(
    // Page 385 (25 * 5: velar nasals have been shifted according to dictionary order)
    Seq(K, K), Seq(K, Kh), Seq(K, C), Seq(K, RetroflexN), Seq(K, DentalT),
    Seq(K, DentalT, Y), Seq(K, DentalT, R), Seq(K, DentalT, R, Y), Seq(K, DentalT, V), Seq(K, DentalTh),
    Seq(K, DentalN), Seq(K, DentalN, Y), Seq(K, M), Seq(K, Y), Seq(K, R),
    Seq(K, L), Seq(K, V), Seq(K, V, Y), Seq(K, RetroflexS), Seq(K, RetroflexS, M),
    Seq(K, RetroflexS, Y), Seq(K, RetroflexS, V), Seq(Kh, Y), Seq(Kh, R), Seq(G, DentalD),

    Seq(G, DentalDh), Seq(G, DentalN), Seq(G, M), Seq(G, R), Seq(Gh, DentalN),
    Seq(Gh, M), Seq(Gh, Y), Seq(Gh, R), Seq(VelarN, K), Seq(VelarN, Kh),
    Seq(VelarN, G), Seq(VelarN, Gh), Seq(C, C), Seq(C, Ch), Seq(C, Ch, R),
    Seq(C, Y), Seq(Ch, Y), Seq(Ch, R), Seq(J, PalatalN), Seq(J, PalatalN, Y),
    Seq(J, M), Seq(J, Y), Seq(J, R), Seq(J, V), Seq(PalatalN, C),

    Seq(PalatalN, Ch), Seq(PalatalN, J), Seq(PalatalN, J, Y), Seq(RetroflexT, RetroflexT), Seq(RetroflexT, Y),
    Seq(RetroflexTh, Y), Seq(RetroflexTh, R), Seq(RetroflexDh, Y), Seq(RetroflexDh, R), Seq(RetroflexN, RetroflexT),
    Seq(RetroflexN, RetroflexTh), Seq(RetroflexN, RetroflexD), Seq(RetroflexN, RetroflexD, Y), Seq(RetroflexN, RetroflexD, R), Seq(RetroflexN, RetroflexDh),
    Seq(RetroflexN, RetroflexN), Seq(RetroflexN, Y), Seq(RetroflexN, V), Seq(DentalT, K), Seq(DentalT, DentalT),
    Seq(DentalT, DentalT, Y), Seq(DentalT, DentalT, R), Seq(DentalT, DentalT, V), Seq(DentalT, DentalTh), Seq(DentalT, DentalN),

    Seq(DentalT, P), Seq(DentalT, P, R), Seq(DentalT, M), Seq(DentalT, M, Y), Seq(DentalT, Y),
    Seq(DentalT, R), Seq(DentalT, R, Y), Seq(DentalT, V), Seq(DentalT, S), Seq(DentalT, S, DentalN),
    Seq(DentalT, S, DentalN, Y), Seq(DentalTh, Y), Seq(DentalD, G), Seq(DentalD, G, R), Seq(DentalD, Gh, R),
    Seq(DentalD, DentalD), Seq(DentalD, DentalDh), Seq(DentalD, DentalN), Seq(DentalD, B), Seq(DentalD, Bh),
    Seq(DentalD, Bh, Y), Seq(DentalD, M), Seq(DentalD, Y), Seq(DentalD, R), Seq(DentalD, R, Y),

    Seq(DentalD, V), Seq(DentalD, V, Y), Seq(DentalDh, DentalN), Seq(DentalDh, DentalN, Y), Seq(DentalDh, M),
    Seq(DentalDh, Y), Seq(DentalDh, R), Seq(DentalDh, R, Y), Seq(DentalDh, V), Seq(DentalN, DentalT),
    Seq(DentalN, DentalT, Y), Seq(DentalN, DentalT, R), Seq(DentalN, DentalD), Seq(DentalN, DentalD, R), Seq(DentalN, DentalDh),
    Seq(DentalN, DentalDh, R), Seq(DentalN, DentalN), Seq(DentalN, P), Seq(DentalN, P, R), Seq(DentalN, M),
    Seq(DentalN, Y), Seq(DentalN, R), Seq(P, DentalT), Seq(P, DentalT, Y), Seq(P, DentalN),

    // Page 386 (full columns: 4 * 18)
    Seq(P, P), Seq(P, M), Seq(P, Y), Seq(P, R), Seq(P, L), Seq(P, V),
    Seq(P, S), Seq(P, S, V), Seq(B, Gh), Seq(B, J), Seq(B, DentalD), Seq(B, DentalDh),
    Seq(B, DentalN), Seq(B, B), Seq(B, Bh), Seq(B, Bh, Y), Seq(B, Y), Seq(B, R),

    Seq(B, V), Seq(Bh, DentalN), Seq(Bh, Y), Seq(Bh, R), Seq(Bh, V), Seq(M, DentalN),
    Seq(M, P), Seq(M, P, R), Seq(M, B), Seq(M, Bh), Seq(M, M), Seq(M, Y),
    Seq(M, R), Seq(M, L), Seq(M, V), Seq(Y, Y), Seq(Y, R), Seq(Y, V),

    Seq(L, K), Seq(L, P), Seq(L, M), Seq(L, Y), Seq(L, L), Seq(L, V),
    Seq(V, Y), Seq(V, R), Seq(V, V), Seq(PalatalS, C), Seq(PalatalS, R), Seq(PalatalS, R, Y),
    Seq(PalatalS, L), Seq(PalatalS, V), Seq(PalatalS, V, Y), Seq(RetroflexS, RetroflexT), Seq(RetroflexS, RetroflexT, Y), Seq(RetroflexS, RetroflexT, R),

    Seq(RetroflexS, RetroflexTh), Seq(RetroflexS, RetroflexN), Seq(RetroflexS, RetroflexN, Y), Seq(RetroflexS, P), Seq(RetroflexS, P, R), Seq(RetroflexS, M),
    Seq(RetroflexS, Y), Seq(RetroflexS, V), Seq(S, K), Seq(S, Kh), Seq(S, DentalT), Seq(S, DentalT, Y),
    Seq(S, DentalT, R), Seq(S, DentalT, V), Seq(S, DentalTh), Seq(S, DentalN), Seq(S, DentalN, Y), Seq(S, P),

    // Page 386 partial column (14)
    Seq(S, Ph), Seq(S, M), Seq(S, M, Y), Seq(S, Y), Seq(S, R), Seq(S, V), Seq(S, S),
    Seq(H, RetroflexN), Seq(H, DentalN), Seq(H, M), Seq(H, Y), Seq(H, R), Seq(H, L), Seq(H, V)
  )
}
