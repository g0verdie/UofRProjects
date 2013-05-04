function [A C] = rowAdd(A,C,R1,R2,S)
%R1*s is added ro R2
C(R2,:) = C(R2,:) + C(R1,:)*S;
A(R2,:) = A(R2,:) + A(R1,:)*S;
end

