function [A] = randMat(m,n)
%generate a matrix of size m of random values between -1 and 1
A = ones(m,n);
for i = 1:m,
    for j = 1:n,
        A(i,j) = 100*(rand(1)*2-1);
    end
end
end

