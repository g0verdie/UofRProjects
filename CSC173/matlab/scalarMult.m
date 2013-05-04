function [A C] = scalarMult(A,C,R,S)
%multiplies row R of A by S
A(R,:) = A(R,:)*S;
C(R,:) = C(R,:)*S;

end

